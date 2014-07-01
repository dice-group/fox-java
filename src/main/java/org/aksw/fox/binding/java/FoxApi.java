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

import org.apache.log4j.Logger;

public class FoxApi implements IFoxApi {

    public final static Logger LOG           = Logger.getLogger(FoxApi.class);

    public static final String defaultApiUrl = "http://139.18.2.164:4444/api";
    private String             apiUrl        = defaultApiUrl;

    private String             input         = "";
    private String             type          = "";
    private String             task          = "";
    private String             output        = "";

    private FoxResponse        foxRes        = null;

    public static void main(String[] a) throws Exception {

    }

    @Override
    public IFoxApi setApiURL(URL url) {
        apiUrl = url.toExternalForm();
        return this;
    }

    @Override
    public IFoxApi setInput(String input) {
        type = FoxParameter.inputs.get(FoxParameter.INPUT.TEXT);
        this.input = input;
        return this;
    }

    @Override
    public IFoxApi setInput(URL url) {
        type = FoxParameter.inputs.get(FoxParameter.INPUT.URL);
        this.input = url.toExternalForm();
        return this;
    }

    @Override
    public IFoxApi setTask(FoxParameter.TASK task) {
        this.task = FoxParameter.tasks.get(task);
        return this;
    }

    @Override
    public IFoxApi setOutputFormat(FoxParameter.OUTPUT output) {
        this.output = FoxParameter.outputs.get(output);
        return this;
    }

    private String getParameter() {
        try {
            return FoxParameter.inputKey.
                    concat("=").
                    concat(URLEncoder.encode(input, FoxResponse.charset)).
                    concat("&").
                    concat(FoxParameter.typeKey).
                    concat("=").
                    concat(type).
                    concat("&").
                    concat(FoxParameter.taskKey).
                    concat("=").
                    concat(task).
                    concat("&").
                    concat(FoxParameter.outputKey).
                    concat("=").
                    concat(output);
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
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=".concat(FoxResponse.charset.toLowerCase()));
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

    /*    public void bla() {
            Model model = ModelFactory.createDefaultModel();
            try {
                model.read(new StringReader(foxRes.getOutput().trim()), "", output);
            } catch (UnsupportedEncodingException e) {
                String error = "Encoding unsupported.";
                LOG.error(error, e);
                throw new RuntimeException(error, e);
            }

            ResIterator datasets = model.listSubjectsWithProperty(RDF.type, model.getProperty(FoxOnto.getAnnotationURI()));
            while (datasets.hasNext()) {
                Resource dataset = datasets.next();

                Statement stmt = dataset.getProperty(model.getProperty(FoxOnto.getBodyURI()));
                System.out.println(stmt.getString());

                // StmtIterator stmts = dataset.listProperties();
                // System.out.println("* " + dataset);
                // while (stmts.hasNext()) {
                // System.out.println("** " + stmts.next());
                // }

            }
            return this;
        }*/
}
