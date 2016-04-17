package com.kxw.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * {<a href='http://www.cnblogs.com/yangy608/p/3936848.html'>@link</a>}
 * jackson 实体转json 为NULL或者为空不参加序列化
 */
public class TestJacksonAnnotation {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Map map = new HashMap();
        map.put("a", null);
        map.put("b", "b");

        String ret_val = mapper.writeValueAsString(map);
        System.err.println(ret_val);
        Map m = mapper.readValue(ret_val, Map.class);
        System.err.println(m.get("a") + "|" + m.get("b"));

    }
}

/**
 * 1.实体上

 @JsonInclude(Include.NON_NULL)

 //将该标记放在属性上，如果该属性为NULL则不参与序列化
 //如果放在类上边,那对这个类的全部属性起作用
 //Include.Include.ALWAYS 默认
 //Include.NON_DEFAULT 属性为默认值不序列化
 //Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
 //Include.NON_NULL 属性为NULL 不序列化


 2.代码上
 ObjectMapper mapper = new ObjectMapper();

 mapper.setSerializationInclusion(Include.NON_NULL);

 //通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
 //Include.Include.ALWAYS 默认
 //Include.NON_DEFAULT 属性为默认值不序列化
 //Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
 //Include.NON_NULL 属性为NULL 不序列化

 User user = new User(1,"",null);
 String outJson = mapper.writeValueAsString(user);
 System.out.println(outJson);
 */
