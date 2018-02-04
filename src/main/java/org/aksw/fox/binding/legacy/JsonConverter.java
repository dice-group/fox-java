package org.aksw.fox.binding.legacy;

import com.google.gson.Gson;

/**
 *
 * @author Ren&eacute; Speck <speck@informatik.uni-leipzig.de>
 *
 */
public class JsonConverter {

  protected static Gson gson = new Gson();

  public static String objectToJson(final Object o) {
    return gson.toJson(o);
  }

  public static Object jsonToObject(final String json, final Class<?> classs) {
    return gson.fromJson(json, classs);
  }
}
