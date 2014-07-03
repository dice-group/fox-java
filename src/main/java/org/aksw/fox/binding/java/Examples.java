package org.aksw.fox.binding.java;

import java.net.MalformedURLException;
import java.net.URL;

public class Examples {

    public static void main(String[] a) throws Exception {
        example_two();
    }

    public static void example_one() throws MalformedURLException {

        URL api = new URL("http", "0.0.0.0", 4443, "/api");
        URL in = new URL("http", "en.wikipedia.org", 80, "/wiki/Leipzig_University");

        IFoxApi fox = new FoxApi();
        FoxResponse response = fox.
                setApiURL(api).
                setInput(in).
                setOutputFormat(FoxParameter.OUTPUT.JSONLD).
                setTask(FoxParameter.TASK.NER).
                send();

        System.out.println(response.getOutput());
    }

    public static void example_two() {

        IFoxApi fox = new FoxApi();
        FoxResponse response = fox.
                setInput("The philosopher and mathematician Gottfried Wilhelm Leibniz was born in Leipzig in 1646 and attended the University of Leipzig from 1661-1666.").
                setLightVersion(FoxParameter.FOXLIGHT.NERStanford).
                send();

        System.out.println(response.getInput());
        System.out.println(response.getOutput());
        System.out.println(response.getLog());
    }
}