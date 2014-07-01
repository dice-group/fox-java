package org.aksw.fox.binding.java;

import java.net.URL;

public interface IFoxApi {

    public IFoxApi setApiURL(URL url);

    public IFoxApi setInput(String input);

    public IFoxApi setInput(URL url);

    // remove this and make it auto.
    // public IFoxWebApi setInputType(FoxParameter.INPUT type);

    public IFoxApi setTask(FoxParameter.TASK task);

    public IFoxApi setOutputFormat(FoxParameter.OUTPUT out);

    public FoxResponse send();

}
