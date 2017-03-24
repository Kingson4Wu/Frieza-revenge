package com.kxw.jackson;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kxw.bean.Kingson;

/**
 * Convert Object to Map example
 * <a href='https://www.mkyong.com/java/java-convert-object-to-map-example/'>@link</a>
 */
public class ObjectToMap {

    public static void main(String[] args) {
        ObjectMapper oMapper = new ObjectMapper();

        Kingson obj = new Kingson();
        obj.setName("mkyong");
        obj.setId(34);

        // object -> Map
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        System.out.println(map);
    }
}
