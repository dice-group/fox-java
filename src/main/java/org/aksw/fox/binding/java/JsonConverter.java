package org.aksw.fox.binding.java;

import com.google.gson.Gson;

public class JsonConverter {

    protected static Gson gson = new Gson();

    public static String objectToJson(Object o) {
        return gson.toJson(o);
    }

    public static Object jsonToObject(String json, Class<?> classs) {
        return gson.fromJson(json, classs);
    }
}
