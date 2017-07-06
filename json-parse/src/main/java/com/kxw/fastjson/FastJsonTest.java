package com.kxw.fastjson;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.kxw.bean.Kingson;

public class FastJsonTest {
    public static void main(String[] args) {
        Map<Integer, Kingson> map = new HashMap<>();
        Kingson kingson = new Kingson();
        kingson.setAge(25);
        map.put(1, kingson);
        map.put(2, kingson);

        System.out.println(JSON.toJSONString(map));
        System.out.println(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
        /**
         * <a href='https://github.com/alibaba/fastjson/issues/120'>@link</a>
         */

    }
}
