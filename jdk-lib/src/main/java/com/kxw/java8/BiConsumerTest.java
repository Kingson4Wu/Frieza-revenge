package com.kxw.java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by kingson.wu on 2016/2/17.
 */
public class BiConsumerTest {

    public static void main(String[] args) {
        BiConsumer<String, String> c = (message, error) -> {
            System.out.println(message);
            System.out.println(error);
        };

        BiConsumer<String, String> cc = (message, error) -> {
            System.out.println("miao" + message);
            System.out.println(error + "miao");
        };

        c.accept("kxw", "kk");

        System.out.println("--------------------");
        BiConsumer<String, String> ccc = c.andThen(cc);
        ccc.accept("dd", "tt");

        Map<String, String> params = new HashMap(){{

            put("kxw","jjj");
            put("kk","dddd");
        }};



        params.forEach((key, value) -> System.out.println(key+":"+value));

        System.out.println(List.class.isAssignableFrom(List.class));
    }
}
