package org.aksw.fox.binding.java;

import java.net.MalformedURLException;
import java.net.URL;

public class Examples {

  public static void main(String[] a) throws Exception {
    example_two();
  }

  public static void example_one() throws MalformedURLException {
    URL api = new URL("http://0.0.0.0:4444/api");
    IFoxApi fox;
    fox = new FoxApi().setApiURL(api)
        .setInput(new URL("https://en.wikipedia.org/wiki/Leipzig_University"))
        .setOutputFormat(FoxParameter.OUTPUT.JSONLD);

    FoxResponse response = fox.send();

    System.out.println(response.getOutput());
  }

  public static void example_two() throws MalformedURLException {
    IFoxApi fox = new FoxApi();
    // URL api = new URL("http://0.0.0.0:4444/api");
    // fox.setApiURL(api);
    fox.setTask(FoxParameter.TASK.NER).setOutputFormat(FoxParameter.OUTPUT.TURTLE)
        .setLang(FoxParameter.LANG.EN)
        .setInput("The philosopher and mathematician Leibniz was born in Leipzig.")
        // .setLightVersion(FoxParameter.FOXLIGHT.ENStanford)
        ;

    FoxResponse response = fox.send();

    System.out.println(response.getInput());
    System.out.println(response.getOutput());
    System.out.println(response.getLog());
  }
}
