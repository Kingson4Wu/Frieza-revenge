package com.kxw.jackson;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kxw.bean.Kingson;

/**
 * Convert Object to Map example
 * <a href='https://www.mkyong.com/java/java-convert-object-to-map-example/'>@link</a>
 */
public class ObjectToMap {

    public static void main(String[] args) {
        ObjectMapper oMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Boolean.class, new NumericBooleanSerializer());
        module.addSerializer(Date.class, new TimestampDateSerializer());
        oMapper.registerModule(module);

        Kingson obj = new Kingson();
        obj.setName("mkyong");
        obj.setId(34);
        obj.setGameOpen("ddddd");
        obj.setBirthDay(new Date());
        obj.setDeleted(false);

        // object -> Map
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        System.out.println(map);

        /**
         * 缺点:
         * Map map2 = oMapper.convertValue(awardInfo, Map.class);
         先把对象转json，date类型丢失，后面有json转map，由于类型擦除，无法识别Date类型，进行转化
         */

        /**
         * java实现map和object互转的三种方法:<a href='http://www.open-open.com/code/view/1423280939826'>@link</>
         */
    }
}


