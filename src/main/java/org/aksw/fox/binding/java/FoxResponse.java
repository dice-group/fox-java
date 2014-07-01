package org.aksw.fox.binding.java;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Data class to work with json.
 * 
 * @author rspeck
 * 
 */
public class FoxResponse {

    public static String charset = "UTF-8";

    protected String     input   = "";
    protected String     output  = "";
    protected String     log     = "";

    public String getInput() throws UnsupportedEncodingException {
        return URLDecoder.decode(input, charset);
    }

    public String getOutput() throws UnsupportedEncodingException {
        return URLDecoder.decode(output, charset);
    }

    public String getLog() throws UnsupportedEncodingException {
        return URLDecoder.decode(log, charset);
    }
}
