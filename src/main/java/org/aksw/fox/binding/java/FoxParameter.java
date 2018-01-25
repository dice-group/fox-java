package org.aksw.fox.binding.java;

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
    NER;
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

  public static EnumMap<LANG, String> langs = new EnumMap<LANG, String>(LANG.class) {

    private static final long serialVersionUID = -2227261015557322237L;

    {
      put(LANG.DE, "de");
      put(LANG.EN, "en");
      put(LANG.FR, "fr");
      put(LANG.ES, "es");
      put(LANG.NL, "nl");
      // put(LANG.IT, "it");
    }
  };

  public static EnumMap<FOXLIGHT, String> foxlights =
      new EnumMap<FOXLIGHT, String>(FOXLIGHT.class) {
        private static final long serialVersionUID = -3143629487718074037L;

        {
          put(FOXLIGHT.OFF, "off");
        }

        {
          put(FOXLIGHT.DESpotlight, "org.aksw.fox.tools.ner.de.SpotlightDE");
          put(FOXLIGHT.DEStanford, "org.aksw.fox.tools.ner.de.StanfordDE");
          put(FOXLIGHT.DEBalie, "org.aksw.fox.tools.ner.de.BalieDE");
        }

        {
          put(FOXLIGHT.ENStanford, "org.aksw.fox.tools.ner.en.StanfordEN");
          put(FOXLIGHT.ENBalie, "org.aksw.fox.tools.ner.en.BalieEN");
          put(FOXLIGHT.ENOpenNLP, "org.aksw.fox.tools.ner.en.OpenNLPEN");
          put(FOXLIGHT.ENIllinoise, "org.aksw.fox.tools.ner.en.IllinoisExtendedEN");
        }

        {
          put(FOXLIGHT.FRBalie, "org.aksw.fox.tools.ner.fr.BalieFR");
          put(FOXLIGHT.FRSpotlight, "org.aksw.fox.tools.ner.fr.SpotlightFR");
          put(FOXLIGHT.FROpenNLP, "org.aksw.fox.tools.ner.fr.OpenNLPFR");
        }

        {
          put(FOXLIGHT.NLSpotlight, "org.aksw.fox.tools.ner.nl.SpotlightNL");
          put(FOXLIGHT.NLOpenNLP, "org.aksw.fox.tools.ner.nl.OpenNLPNL");
        }
      };

  public static EnumMap<OUTPUT, String> outputs = new EnumMap<OUTPUT, String>(OUTPUT.class) {
    private static final long serialVersionUID = -634863251164405026L;

    {
      put(OUTPUT.RDFXML, Lang.RDFXML.getName());
      put(OUTPUT.TURTLE, Lang.TURTLE.getName());
      put(OUTPUT.NTRIPLES, Lang.NTRIPLES.getName());
      put(OUTPUT.RDFJSON, Lang.RDFJSON.getName());
      put(OUTPUT.JSONLD, Lang.JSONLD.getName());
      put(OUTPUT.TRIG, Lang.TRIG.getName());
      put(OUTPUT.NQUADS, Lang.NQUADS.getName());
    }
  };

  public static EnumMap<INPUT, String> inputs = new EnumMap<INPUT, String>(INPUT.class) {
    private static final long serialVersionUID = -7480568398834502150L;

    {
      put(INPUT.TEXT, "text");
      put(INPUT.URL, "url");

    }
  };

  public static EnumMap<TASK, String> tasks = new EnumMap<TASK, String>(TASK.class) {
    private static final long serialVersionUID = -319723597765821322L;

    {
      put(TASK.NER, "NER");

    }
  };

  public static EnumMap<HTML, String> htmls = new EnumMap<HTML, String>(HTML.class) {
    private static final long serialVersionUID = -7593509777668400057L;

    {
      put(HTML.TRUE, "true");
      put(HTML.FALSE, "false");

    }
  };

}
