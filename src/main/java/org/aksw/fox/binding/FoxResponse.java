package org.aksw.fox.binding;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.aksw.fox.data.Entity;
import org.aksw.fox.data.RelationSimple;
import org.aksw.fox.data.Voc;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.RDF;
import org.apache.log4j.Logger;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public class FoxResponse {
  public final static Logger LOG = Logger.getLogger(FoxResponse.class);
  public static final String StandardCharsetName = StandardCharsets.UTF_8.name();

  List<Entity> entities = new ArrayList<>();
  List<RelationSimple> relations = new ArrayList<>();

  Model model = null;

  public FoxResponse(final String response, final FoxParameter.OUTPUT out) {
    try {
      model = ModelFactory.createDefaultModel();
      model.read(new StringReader(response), null, FoxParameter.outputs.get(out));
    } catch (final Exception e) {
      LOG.error(e.getLocalizedMessage(), e);
    }

    if (model != null) {

      parseEntities();
      parseRelations();

    } else {
      throw new UnsupportedOperationException("Model could not loaded.");
    }
  }

  public void print(final Model model) {
    final ByteArrayOutputStream ba = new ByteArrayOutputStream();
    RDFDataMgr.write(ba, model, RDFFormat.TURTLE_PRETTY);
    LOG.info(new String(ba.toByteArray(), StandardCharsets.UTF_8));
  }

  private void parseEntities() {

    final ResIterator i = model.listResourcesWithProperty(RDF.type, Voc.pNifPhrase);
    while (i.hasNext()) {
      final Resource r = i.next();

      String type = null;
      final StmtIterator ii = r.listProperties(Voc.pItsrdfTaClassRef);
      while (ii.hasNext()) {
        final Statement statement = ii.next();
        type = statement.getObject().asResource().getURI().toString();
        if ((type != null) && type.startsWith(Voc.ns_fox_ontology)) {
          break;
        } else {
          type = null;
        }
      }
      if (type != null) {

        final String text = r.getProperty(Voc.pNifAnchorOf).getObject().asLiteral().toString();
        final int index = r.getProperty(Voc.pNifBegin).getObject().asLiteral().getInt();
        final String uri =
            r.getProperty(Voc.pItsrdfTaIdentRef).getObject().asResource().getURI().toString();

        final Entity entity = new Entity(text, type);
        entity.addIndicies(index);
        entity.setUri(uri);
        entities.add(entity);
      }
    }
  }

  private void parseRelations() {

    final ResIterator i = model.listResourcesWithProperty(RDF.type, Voc.pFoxRelation);
    while (i.hasNext()) {
      final Resource r = i.next();
      final String s = r.getProperty(Voc.pRdfSubject).getObject().asResource().getURI().toString();
      final String p =
          r.getProperty(Voc.pRdfPredicate).getObject().asResource().getURI().toString();
      final String o = r.getProperty(Voc.pRdfObject).getObject().asResource().getURI().toString();
      String tool = "";

      // prov:Activity
      final ResIterator ii = model.listResourcesWithProperty(RDF.type, Voc.pProvActivity);
      while (ii.hasNext()) {
        final Resource rr = ii.next();
        final StmtIterator iii = rr.listProperties(Voc.pProvGenerated);
        while (iii.hasNext()) {
          final Statement statement = iii.next();
          // all uris
          final String rRes = statement.getObject().asResource().getURI().toString();
          if (r.getURI().toString().equals(rRes)) {
            tool = rr.getProperty(Voc.pProvUsed).getObject().asResource().getURI().toString();
            break;
          }
        }
        if (!tool.isEmpty()) {
          break;
        }
      }
      final RelationSimple relationSimple = new RelationSimple(s, p, o, tool);
      relations.add(relationSimple);
    }
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public List<RelationSimple> getRelations() {
    return relations;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();

    sb.append("Entities: ").append(System.getProperty("line.separator"));
    entities.forEach(e -> sb.append(e).append(System.getProperty("line.separator")));

    sb.append("Relations: ").append(System.getProperty("line.separator"));
    relations.forEach(e -> sb.append(e).append(System.getProperty("line.separator")));
    return sb.toString();
  }

}
