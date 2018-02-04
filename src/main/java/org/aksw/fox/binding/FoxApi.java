package org.aksw.fox.binding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.aksw.fox.binding.FoxParameter.FOXLIGHT;
import org.aksw.fox.binding.FoxParameter.INPUT;
import org.aksw.fox.binding.FoxParameter.LANG;
import org.aksw.fox.binding.FoxParameter.OUTPUT;
import org.aksw.fox.binding.FoxParameter.TASK;
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
  private INPUT type = INPUT.TEXT;
  private TASK task = TASK.NER;
  private FOXLIGHT light = FOXLIGHT.OFF;
  private OUTPUT output = OUTPUT.TURTLE;
  private LANG lang = LANG.EN;

  protected String plainResponse = null;

  @Override
  public IFoxApi setApiURL(final URL url) {
    apiUrl = url.toExternalForm();
    return this;
  }

  @Override
  public IFoxApi setInput(final String input) {
    type = (INPUT.TEXT);
    this.input = input;
    return this;
  }

  @Override
  public IFoxApi setInput(final URL url) {
    type = (INPUT.URL);
    input = url.toExternalForm();
    return this;
  }

  @Override
  public IFoxApi setTask(final TASK task) {
    this.task = (task);
    return this;
  }

  @Override
  public IFoxApi setLightVersion(final FOXLIGHT foxlight) {
    light = (foxlight);
    return this;
  }

  @Override
  public IFoxApi setOutputFormat(final OUTPUT output) {
    this.output = output;
    return this;
  }

  @Override
  public IFoxApi setLang(final LANG lang) {
    this.lang = lang;
    return this;
  }

  protected String getParameter() {
    return new JSONObject()//
        .put(FoxParameter.inputKey, input)//
        .put(FoxParameter.typeKey, FoxParameter.inputs.get(type))//
        .put(FoxParameter.taskKey, FoxParameter.tasks.get(task))//
        .put(FoxParameter.langKey, FoxParameter.langs.get(lang))//
        .put(FoxParameter.foxlightKey, FoxParameter.foxlights.get(light))//
        .put(FoxParameter.outputKey, FoxParameter.outputs.get(output))//
        .toString();
  }

  @Override
  public IFoxApi send() {
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
    plainResponse = data.toString();
    return this;
  }

  @Override
  public String responseAsFile() {
    return plainResponse;
  }

  @Override
  public FoxResponse responseAsClasses() {
    return new FoxResponse(plainResponse, output);
  }
}
