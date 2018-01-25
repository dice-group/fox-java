package org.aksw.fox.binding.java;

import java.net.URL;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public interface IFoxApi {

  public IFoxApi setApiURL(URL url);

  public IFoxApi setInput(String input);

  public IFoxApi setInput(URL url);

  public IFoxApi setTask(FoxParameter.TASK task);

  public IFoxApi setLightVersion(FoxParameter.FOXLIGHT foxlight);

  public IFoxApi setOutputFormat(FoxParameter.OUTPUT out);

  public IFoxApi setLang(FoxParameter.LANG lang);

  public String send();

}
