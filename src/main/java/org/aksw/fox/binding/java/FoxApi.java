package org.aksw.fox.binding.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.aksw.fox.binding.java.FoxParameter.FOXLIGHT;
import org.aksw.fox.binding.java.FoxParameter.INPUT;
import org.aksw.fox.binding.java.FoxParameter.LANG;
import org.aksw.fox.binding.java.FoxParameter.OUTPUT;
import org.aksw.fox.binding.java.FoxParameter.TASK;
import org.apache.log4j.Logger;

public class FoxApi implements IFoxApi {

  public final static Logger LOG = Logger.getLogger(FoxApi.class);

  public static final String defaultApiUrl = "http://139.18.2.164:4444/api";
  private String apiUrl = defaultApiUrl;

  // defaults
  private String input = "";
  private String type = FoxParameter.inputs.get(INPUT.TEXT);
  private String task = FoxParameter.tasks.get(TASK.NER);
  private String light = FoxParameter.foxlights.get(FOXLIGHT.OFF);
  private String output = FoxParameter.outputs.get(OUTPUT.TURTLE);
  private String lang = FoxParameter.langs.get(LANG.EN);

  private FoxResponse foxRes = null;

  @Override
  public IFoxApi setApiURL(URL url) {
    apiUrl = url.toExternalForm();
    return this;
  }

  @Override
  public IFoxApi setInput(String input) {
    type = FoxParameter.inputs.get(INPUT.TEXT);
    this.input = input;
    return this;
  }

  @Override
  public IFoxApi setInput(URL url) {
    type = FoxParameter.inputs.get(INPUT.URL);
    this.input = url.toExternalForm();
    return this;
  }

  @Override
  public IFoxApi setTask(TASK task) {
    this.task = FoxParameter.tasks.get(task);
    return this;
  }

  @Override
  public IFoxApi setLightVersion(FOXLIGHT foxlight) {
    this.light = FoxParameter.foxlights.get(foxlight);
    return this;
  }

  @Override
  public IFoxApi setOutputFormat(OUTPUT output) {
    this.output = FoxParameter.outputs.get(output);
    return this;
  }

  @Override
  public IFoxApi setLang(LANG lang) {
    this.lang = FoxParameter.langs.get(lang);
    return this;
  }

  private String getParameter() {
    try {
      String s = "";
      s = s.concat(
          FoxParameter.inputKey.concat("=").concat(URLEncoder.encode(input, FoxResponse.charset)));
      s = s.concat("&");
      s = s.concat(FoxParameter.typeKey).concat("=").concat(type);
      s = s.concat("&");
      s = s.concat(FoxParameter.taskKey).concat("=").concat(task);
      s = s.concat("&");
      s = s.concat(FoxParameter.langKey).concat("=").concat(lang);
      s = s.concat("&");
      s = s.concat(FoxParameter.foxlightKey).concat("=").concat(light);
      s = s.concat("&");
      s = s.concat(FoxParameter.outputKey).concat("=").concat(output);

      return s;
    } catch (UnsupportedEncodingException e) {
      String error = "Encoding unsupported.";
      LOG.error(error, e);
      throw new RuntimeException(error, e);
    }
  }

  @Override
  public FoxResponse send() {
    foxRes = null;
    String body = getParameter();
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) new URL(apiUrl).openConnection();
    } catch (IOException e) {
      String error = "Could not connect!";
      LOG.error(error, e);
      throw new RuntimeException(error, e);
    }
    if (connection != null) {
      try {
        connection.setRequestMethod("POST");
      } catch (ProtocolException e) {
        String error = "Protocol not supported!";
        LOG.error(error, e);
        throw new RuntimeException(error, e);
      }
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setUseCaches(false);
      connection.setRequestProperty("Accept-Charset", FoxResponse.charset);
      connection.setRequestProperty("Content-Type",
          "application/x-www-form-urlencoded; charset=".concat(FoxResponse.charset.toLowerCase()));
      connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

      OutputStreamWriter writer = null;
      try {
        writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();
      } catch (IOException e) {
        String error = "Could not write output stream!";
        LOG.error(error, e);
        throw new RuntimeException(error, e);
      }

      StringBuilder data = new StringBuilder();
      BufferedReader reader;
      try {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        for (String line; (line = reader.readLine()) != null;)
          data.append(line);

        writer.close();
        reader.close();
      } catch (IOException e) {
        String error = "Could not read input stream!";
        LOG.error(error, e);
        throw new RuntimeException(error, e);
      }
      foxRes = (FoxResponse) JsonConverter.jsonToObject(data.toString(), FoxResponse.class);
    }
    return foxRes;
  }
}
