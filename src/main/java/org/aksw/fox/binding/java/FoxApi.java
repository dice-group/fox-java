package org.aksw.fox.binding.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.aksw.fox.binding.java.FoxParameter.FOXLIGHT;
import org.aksw.fox.binding.java.FoxParameter.INPUT;
import org.aksw.fox.binding.java.FoxParameter.LANG;
import org.aksw.fox.binding.java.FoxParameter.OUTPUT;
import org.aksw.fox.binding.java.FoxParameter.TASK;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public class FoxApi implements IFoxApi {

  public final static Logger LOG = Logger.getLogger(FoxApi.class);

  private String apiUrl = null;

  // defaults
  private String input = "";
  private String type = FoxParameter.inputs.get(INPUT.TEXT);
  private String task = FoxParameter.tasks.get(TASK.NER);
  private String light = FoxParameter.foxlights.get(FOXLIGHT.OFF);
  private String output = FoxParameter.outputs.get(OUTPUT.TURTLE);
  private String lang = FoxParameter.langs.get(LANG.EN);

  @Override
  public IFoxApi setApiURL(final URL url) {
    apiUrl = url.toExternalForm();
    return this;
  }

  @Override
  public IFoxApi setInput(final String input) {
    type = FoxParameter.inputs.get(INPUT.TEXT);
    this.input = input;
    return this;
  }

  @Override
  public IFoxApi setInput(final URL url) {
    type = FoxParameter.inputs.get(INPUT.URL);
    input = url.toExternalForm();
    return this;
  }

  @Override
  public IFoxApi setTask(final TASK task) {
    this.task = FoxParameter.tasks.get(task);
    return this;
  }

  @Override
  public IFoxApi setLightVersion(final FOXLIGHT foxlight) {
    light = FoxParameter.foxlights.get(foxlight);
    return this;
  }

  @Override
  public IFoxApi setOutputFormat(final OUTPUT output) {
    this.output = FoxParameter.outputs.get(output);
    return this;
  }

  @Override
  public IFoxApi setLang(final LANG lang) {
    this.lang = FoxParameter.langs.get(lang);
    return this;
  }

  protected String getParameter() {
    return new JSONObject()//
        .put(FoxParameter.inputKey, input)//
        .put(FoxParameter.typeKey, type)//
        .put(FoxParameter.taskKey, task)//
        .put(FoxParameter.langKey, lang)//
        .put(FoxParameter.foxlightKey, light)//
        .put(FoxParameter.outputKey, output)//
        .toString();
  }

  @Override
  public String send() {
    if (apiUrl == null) {
      throw new NullPointerException("ApiURL not set!");
    }

    final String body = getParameter();
    final StringBuilder data = new StringBuilder();
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) new URL(apiUrl).openConnection();
    } catch (final IOException e) {
      final String error = "Could not connect!";
      LOG.error(error, e);
      throw new RuntimeException(error, e);
    }
    if (connection != null) {
      try {
        connection.setRequestMethod("POST");
      } catch (final ProtocolException e) {
        final String error = "Protocol not supported!";
        LOG.error(error, e);
        throw new RuntimeException(error, e);
      }
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setUseCaches(false);
      connection.setRequestProperty("Accept-Charset", FoxResponse.StandardCharsetName);
      connection.setRequestProperty("Content-Type",
          "application/json; charset=".concat(FoxResponse.StandardCharsetName.toLowerCase()));
      connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

      OutputStreamWriter writer = null;
      try {
        writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();
      } catch (final IOException e) {
        final String error = "Could not write output stream!";
        LOG.error(error, e);
        throw new RuntimeException(error, e);
      }
      BufferedReader reader;
      try {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        for (String line; (line = reader.readLine()) != null;) {
          data.append(line);
          data.append(System.lineSeparator());
        }
        writer.close();
        reader.close();
      } catch (final IOException e) {
        final String error = "Could not read input stream!";
        LOG.error(error, e);
        throw new RuntimeException(error, e);
      }
    }
    return data.toString();
  }
}
