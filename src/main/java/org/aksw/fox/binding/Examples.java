package org.aksw.fox.binding;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public class Examples {
  public final static Logger LOG = LogManager.getLogger(Examples.class);

  public static void main(final String[] a) throws Exception {
    example2();
  }

  public static void example2() throws MalformedURLException {

    final IFoxApi fox = new FoxApi()//
        .setApiURL(new URL("http://fox-demo.aksw.org/fox"))//
        .setTask(FoxParameter.TASK.RE)//
        .setOutputFormat(FoxParameter.OUTPUT.JSONLD)//
        .setLang(FoxParameter.LANG.EN)//
        .setInput("A. Einstein was born in Ulm. Einstein was married to Elsa Einstein.")//
        // .setLightVersion(FoxParameter.FOXLIGHT.ENBalie)//
        .send();

    final String plainContent = fox.responseAsFile();
    final FoxResponse response = fox.responseAsClasses();
    // Set<Entity> entities = response.getEntities();
    // Set<Relation> relations = response.getRelations();

    LOG.info(plainContent);
    LOG.info(response);
  }

  public static void example() throws MalformedURLException {

    final IFoxApi fox = new FoxApi();
    fox.setApiURL(new URL("http://fox-demo.aksw.org/fox"));

    fox.setTask(FoxParameter.TASK.NER);
    fox.setOutputFormat(FoxParameter.OUTPUT.TURTLE);
    fox.setLang(FoxParameter.LANG.DE);
    fox.setInput("Die Universität Leipzig liegt in Sachsen.");
    // fox.setLightVersion(FoxParameter.FOXLIGHT.DEBalie);
    LOG.info(fox.send().responseAsFile());
  }
}
