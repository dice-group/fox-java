package org.aksw.fox.binding.java;

import java.net.MalformedURLException;
import java.net.URL;

public class Examples {

  public static void main(String[] a) throws Exception {
    example_two();
  }

  public static void example_one() throws MalformedURLException {

    IFoxApi fox = new FoxApi();
    // URL api = new URL("http://0.0.0.0:4444/api");
    // fox.setApiURL(api);

    fox.setInput(new URL("https://en.wikipedia.org/wiki/Leipzig_University"));
    fox.setOutputFormat(FoxParameter.OUTPUT.JSONLD);

    FoxResponse response = fox.send();
    System.out.println(response.getOutput());
  }

  public static void example_two() throws MalformedURLException {

    IFoxApi fox = new FoxApi();
    URL api = new URL("http://0.0.0.0:4444/api");
    fox.setApiURL(api);

    fox.setTask(FoxParameter.TASK.NER);
    fox.setOutputFormat(FoxParameter.OUTPUT.TURTLE);
    fox.setLang(FoxParameter.LANG.EN);
    fox.setInput("The philosopher and mathematician Leibniz was born in Leipzig.");
    // fox.setLightVersion(FoxParameter.FOXLIGHT.ENStanford);

    FoxResponse response = fox.send();

    System.out.println(response.getInput());
    System.out.println(response.getOutput());
    System.out.println(response.getLog());
  }
}
