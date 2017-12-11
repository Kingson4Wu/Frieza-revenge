package com.kxw.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kxw.bean.Kingson;
import com.kxw.bean.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wukunxin@bigdoctor.com
 * @date 2017/12/8 上午11:07
 * @description
 * @comment
 */
public class JacksonComplexObject {

    public static void main(String[] args) throws IOException {
        Kingson kingson = new Kingson();

        Person person = new Person("kk",34);
        kingson.setPerson(person);

        Person person1 = new Person("kk1",35);
        Person person2 = new Person("kk2",6);
        Person person3 = new Person("kk3",37);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);

        kingson.setPersonList(list);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(kingson);
        System.out.println(json);

        //Kingson kxw = objectMapper.readValue(json, new TypeReference<Kingson>() {});
        Kingson kxw = objectMapper.readValue(json, Kingson.class);

        List<Kingson> kxwList = new ArrayList<>();
        kxwList.add(kxw);
        kxwList.add(kingson);
        json = objectMapper.writeValueAsString(kxwList);
        System.out.println(json);

        List<Kingson> kxwList2 = objectMapper.readValue(json, List.class);


        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, Kingson.class);
        List<Kingson> lst = objectMapper.readValue(json, javaType);



        kingson.setGameOpen("kxwkxw");
        System.out.println(objectMapper.writeValueAsString(kingson));

        //objectMapper.setSerializationInclusion(Inclusion.NON_NULL);

        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String jj = "{\"name\":\"Kingson Wu\",\"game_open\":\"kxwkxw\"}";
        Kingson kk = objectMapper.readValue(jj, Kingson.class);

        System.out.println();
    }



}