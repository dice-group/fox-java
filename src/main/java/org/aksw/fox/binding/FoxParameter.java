package org.aksw.fox.binding;

import java.util.EnumMap;

import org.apache.jena.riot.Lang;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public class FoxParameter {

  public static final String inputKey = "input";
  public static final String outputKey = "output";
  public static final String taskKey = "task";
  public static final String typeKey = "type";
  public static final String htmlKey = "returnHtml";
  public static final String foxlightKey = "foxlight";
  public static final String langKey = "lang";

  public enum OUTPUT {
    RDFXML, TURTLE, NTRIPLES, RDFJSON, JSONLD, TRIG, NQUADS;
  }

  public enum INPUT {
    TEXT, URL;
  }

  public enum TASK {
    NER, RE;
  }

  public enum HTML {
    TRUE, FALSE;
  }

  public enum FOXLIGHT {
    OFF, DESpotlight, DEStanford, DEBalie, ENStanford, ENBalie, ENOpenNLP, ENIllinoise, FRBalie, FRSpotlight, FROpenNLP, NLSpotlight, NLOpenNLP;
  }

  public enum LANG {
    DE, EN, FR, ES, NL;// , IT;
  }

  public static EnumMap<LANG, String> langs = new EnumMap<LANG, String>(LANG.class);
  static {
    langs.put(LANG.DE, "de");
    langs.put(LANG.EN, "en");
    langs.put(LANG.FR, "fr");
    langs.put(LANG.ES, "es");
    langs.put(LANG.NL, "nl");
  }

  public static EnumMap<FOXLIGHT, String> foxlights = new EnumMap<FOXLIGHT, String>(FOXLIGHT.class);
  static {
    foxlights.put(FOXLIGHT.OFF, "off");
    foxlights.put(FOXLIGHT.DESpotlight, "org.aksw.fox.tools.ner.de.SpotlightDE");
    foxlights.put(FOXLIGHT.DEStanford, "org.aksw.fox.tools.ner.de.StanfordDE");
    foxlights.put(FOXLIGHT.DEBalie, "org.aksw.fox.tools.ner.de.BalieDE");
    foxlights.put(FOXLIGHT.ENStanford, "org.aksw.fox.tools.ner.en.StanfordEN");
    foxlights.put(FOXLIGHT.ENBalie, "org.aksw.fox.tools.ner.en.BalieEN");
    foxlights.put(FOXLIGHT.ENOpenNLP, "org.aksw.fox.tools.ner.en.OpenNLPEN");
    foxlights.put(FOXLIGHT.ENIllinoise, "org.aksw.fox.tools.ner.en.IllinoisExtendedEN");
    foxlights.put(FOXLIGHT.FRBalie, "org.aksw.fox.tools.ner.fr.BalieFR");
    foxlights.put(FOXLIGHT.FRSpotlight, "org.aksw.fox.tools.ner.fr.SpotlightFR");
    foxlights.put(FOXLIGHT.FROpenNLP, "org.aksw.fox.tools.ner.fr.OpenNLPFR");
    foxlights.put(FOXLIGHT.NLSpotlight, "org.aksw.fox.tools.ner.nl.SpotlightNL");
    foxlights.put(FOXLIGHT.NLOpenNLP, "org.aksw.fox.tools.ner.nl.OpenNLPNL");
  }

  public static EnumMap<OUTPUT, String> outputs = new EnumMap<OUTPUT, String>(OUTPUT.class);
  static {
    outputs.put(OUTPUT.RDFXML, Lang.RDFXML.getName());
    outputs.put(OUTPUT.TURTLE, Lang.TURTLE.getName());
    outputs.put(OUTPUT.NTRIPLES, Lang.NTRIPLES.getName());
    outputs.put(OUTPUT.RDFJSON, Lang.RDFJSON.getName());
    outputs.put(OUTPUT.JSONLD, Lang.JSONLD.getName());
    outputs.put(OUTPUT.TRIG, Lang.TRIG.getName());
    outputs.put(OUTPUT.NQUADS, Lang.NQUADS.getName());
  }

  public static EnumMap<INPUT, String> inputs = new EnumMap<INPUT, String>(INPUT.class);
  static {
    inputs.put(INPUT.TEXT, "text");
    inputs.put(INPUT.URL, "url");
  }

  public static EnumMap<TASK, String> tasks = new EnumMap<TASK, String>(TASK.class);
  static {
    tasks.put(TASK.NER, "NER");
    tasks.put(TASK.RE, "RE");
  }

  public static EnumMap<HTML, String> htmls = new EnumMap<HTML, String>(HTML.class);
  static {
    htmls.put(HTML.TRUE, "true");
    htmls.put(HTML.FALSE, "false");
  }
}
