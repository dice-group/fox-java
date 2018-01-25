package org.aksw.fox.binding.java;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public class Examples {
  public final static Logger LOG = LogManager.getLogger(Examples.class);

  public static void main(final String[] a) throws Exception {
    example();
  }

  public static void example() throws MalformedURLException {

    final IFoxApi fox = new FoxApi();
    fox.setApiURL(new URL("http://fox-demo.aksw.org/fox"));

    fox.setTask(FoxParameter.TASK.NER);
    fox.setOutputFormat(FoxParameter.OUTPUT.TURTLE);
    fox.setLang(FoxParameter.LANG.DE);
    fox.setInput("Die Universität Leipzig liegt in Sachsen.");
    fox.setLightVersion(FoxParameter.FOXLIGHT.DEBalie);
    LOG.info(fox.send());
  }
}
