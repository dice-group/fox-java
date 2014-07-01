package org.aksw.fox.binding.java;

import java.net.URL;

public class Example {

    public static void main(String[] a) throws Exception {

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
}
