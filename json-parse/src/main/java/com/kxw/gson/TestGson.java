package com.kxw.gson;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by kingson.wu on 2015/12/8.
 */
public class TestGson {

    public static void main(String[] args) {
        String str = "";
        Map<String, Object> preValues = new Gson().fromJson(str, Map.class);
    }
}
