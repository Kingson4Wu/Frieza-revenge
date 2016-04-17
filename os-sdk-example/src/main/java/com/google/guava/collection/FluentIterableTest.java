package com.google.guava.collection;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.kxw.bean.Person;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingson.wu on 2016/1/8.
 */
public class FluentIterableTest {

    public static void main(String[] args) {

        Map<String, String> result = new HashMap<>();
        result.put("kxw", "kingson,10");
        result.put("torres", "fernado,9");
        Map<String, Person> map = FluentIterable.from(result.keySet()).toMap(new Function<String, Person>() {
            @Nullable
            @Override
            public Person apply(@Nullable String key) {
                String[] value = result.get(key).split(",");
                return new Person(value[0], Integer.valueOf(value[1]));
            }
        });

        System.out.println(map);
        for (Map.Entry<String, Person> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().getAge() + " : " + entry.getValue().getName());
        }

    }
}
