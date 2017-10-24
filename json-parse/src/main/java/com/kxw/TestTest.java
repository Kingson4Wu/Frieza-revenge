package com.kxw;

import com.kxw.jackson.JacksonUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TestTest {

    public static void main(String[] args) throws Exception {
        Map<String, byte[]> parameters = new HashMap<>();

        parameters.put("kingson", ((String) "nnnn").getBytes(StandardCharsets.UTF_8));

        System.out.println(JacksonUtils.obj2json(parameters));

    }
}
