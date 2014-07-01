package org.aksw.fox.binding.java;

import java.util.EnumMap;

import org.apache.jena.riot.Lang;

import com.hp.hpl.jena.util.FileUtils;

public class FoxParameter {

    public static final String inputKey    = "input";
    public static final String outputKey   = "output";
    public static final String taskKey     = "task";
    public static final String typeKey     = "type";
    public static final String htmlKey     = "returnHtml";
    public static final String foxlightKey = "foxlight";

    public enum OUTPUT {
        RDFXML, XMLAbbrev, TURTLE, NTRIPLES, N3, RDFJSON, JSONLD, TRIG;
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
        OFF, NERBalie, NERIllinoisExtended, NERStanford, NEROpenNLP;
    }

    public static EnumMap<FOXLIGHT, String> foxlights = new EnumMap<FOXLIGHT, String>(FOXLIGHT.class) {
                                                          private static final long serialVersionUID = -3143629487718074037L;
                                                          {
                                                              put(FOXLIGHT.OFF, "OFF");
                                                              put(FOXLIGHT.NERBalie, "org.aksw.fox.nertools.NERBalie");
                                                              put(FOXLIGHT.NERIllinoisExtended, "org.aksw.fox.nertools.NERIllinoisExtended");
                                                              put(FOXLIGHT.NERStanford, "org.aksw.fox.nertools.NERStanford");
                                                              put(FOXLIGHT.NEROpenNLP, "org.aksw.fox.nertools.NEROpenNLP");
                                                          }
                                                      };

    public static EnumMap<OUTPUT, String>   outputs   = new EnumMap<OUTPUT, String>(OUTPUT.class) {
                                                          private static final long serialVersionUID = -634863251164405026L;
                                                          {
                                                              put(OUTPUT.RDFXML, Lang.RDFXML.getName());
                                                              put(OUTPUT.XMLAbbrev, FileUtils.langXMLAbbrev);
                                                              put(OUTPUT.TURTLE, Lang.TURTLE.getName());
                                                              put(OUTPUT.NTRIPLES, Lang.NTRIPLES.getName());
                                                              put(OUTPUT.N3, Lang.N3.getName());
                                                              put(OUTPUT.RDFJSON, Lang.RDFJSON.getName());
                                                              put(OUTPUT.JSONLD, Lang.JSONLD.getName());
                                                              put(OUTPUT.TRIG, Lang.TRIG.getName());
                                                          }
                                                      };

    public static EnumMap<INPUT, String>    inputs    = new EnumMap<INPUT, String>(INPUT.class) {
                                                          private static final long serialVersionUID = -7480568398834502150L;
                                                          {
                                                              put(INPUT.TEXT, "text");
                                                              put(INPUT.URL, "url");

                                                          }
                                                      };

    public static EnumMap<TASK, String>     tasks     = new EnumMap<TASK, String>(TASK.class) {
                                                          private static final long serialVersionUID = -319723597765821322L;
                                                          {
                                                              put(TASK.NER, "NER");

                                                          }
                                                      };

    public static EnumMap<HTML, String>     htmls     = new EnumMap<HTML, String>(HTML.class) {
                                                          private static final long serialVersionUID = -7593509777668400057L;
                                                          {
                                                              put(HTML.TRUE, "true");
                                                              put(HTML.FALSE, "false");

                                                          }
                                                      };

}
