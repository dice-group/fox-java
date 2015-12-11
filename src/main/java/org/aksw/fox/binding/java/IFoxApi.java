package org.aksw.fox.binding.java;

import java.net.URL;

public interface IFoxApi {

  public IFoxApi setApiURL(URL url);

  public IFoxApi setInput(String input);

  public IFoxApi setInput(URL url);

  public IFoxApi setTask(FoxParameter.TASK task);

  public IFoxApi setLightVersion(FoxParameter.FOXLIGHT foxlight);

  public IFoxApi setOutputFormat(FoxParameter.OUTPUT out);

  public IFoxApi setLang(FoxParameter.LANG lang);


  public FoxResponse send();

}
