package com.kxw.memcached.spymemcached;

import com.alibaba.fastjson.JSON;
import com.kxw.jackson.JacksonUtils;
import com.kxw.memcached.bean.MISC;
import com.kxw.memcached.bean.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2016/5/5.
 */
public class SerializationTest {

    private static SpyMemcachedManager manager;

    private static User bean;

    private static MISC misc;

    public static boolean isHessian = false;

    static {
        manager = new SpyMemcachedManager("127.0.0.1:11211");
        try {
            manager.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bean = new User();
        bean.setUid(223);
        bean.setUname("kingson");
        bean.setUpass("ddd");

        misc = new MISC();
        misc.setName("torres");
        misc.setUser(bean);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("dds" + i);
        }
        misc.setList(list);
        List<User> userlist = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            userlist.add(new User(i, "dd" + i, "df" + i));
        }
        misc.setUsrlist(userlist);


    }

    public static void main(String[] args) {

      /*  manager.set("ss", "dd", 1000);
        String str = (String) manager.get("ss");
        System.out.println(str);*/

    /*    for (int i = 0; i < 100; i++) {
            User user = new User();
            fastjsonSerialization("fastjson-serialization" + i, user);
        }
        for (int i = 0; i < 100; i++) {
            User user = new User();
            jsonSerialization("json-serialization" + i, user);
        }*/
        /*for (int i = 0; i < 100; i++) {
            User user = new User();
            javaSerialization("java-serialization" + i, user);
        }*/


       /* isHessian = true;
        javaSerialization("hessian-serialization", bean);
        isHessian = false;
        User user = new User();
        user.setUname("dd");
        javaSerialization("java-serialization", user);*/
        //hessian 相比jdk的序列化，set慢get快,好像也不是。。。。。

       /* java-serialization set : 41
        java-serialization get : 11
        hessian-serialization set : 76
        hessian-serialization get : 7
        */
        /*isHessian = true;
        long start = System.currentTimeMillis();
        manager.set("hessian-serialization",misc);
        System.out.println("hessian-serialization set : " + String.valueOf(System.currentTimeMillis() - start));

        isHessian = false;
        start = System.currentTimeMillis();
        manager.set("java-serialization",misc);
        System.out.println("java-serialization set : " + String.valueOf(System.currentTimeMillis() - start));
*/

        isHessian = true;
        long start = System.currentTimeMillis();
        for(int i=0;i<10_000;i++){
            MISC u = (MISC) manager.get("hessian-serialization");
        }
        System.out.println("hessian-serialization get : " + String.valueOf(System.currentTimeMillis() - start));
         //hessian-serialization get : 1311
        //hessian-serialization get : 1450 --more data object
       // hessian-serialization get : 7946 --more data object --10_000 times
        /* isHessian = false;
        long start = System.currentTimeMillis();
        for(int i=0;i<10_000;i++){
            MISC u = (MISC) manager.get("java-serialization");
        }
        System.out.println("java-serialization get : " + String.valueOf(System.currentTimeMillis() - start));
        */
        //java-serialization get : 1255
        //java-serialization get : 1789 --more data object
        //java-serialization get : 7961 --more data object --10_000 times


        manager.disConnect();

    }


    private static void javaSerialization(String key, User bean) {

        long start = System.currentTimeMillis();
        manager.set(key, bean);
        System.out.println("java-serialization set : " + String.valueOf(System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        User user = (User) manager.get(key);
        System.out.println("java-serialization get : " + String.valueOf(System.currentTimeMillis() - start));
    }

    private static void jsonSerialization(String key, User bean) {

        try {
            long start = System.currentTimeMillis();
            String json = JacksonUtils.obj2json(bean);

            manager.set(key, json);
            System.out.println("json-serialization set : " + String.valueOf(System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            String json2 = (String) manager.get(key);
            User user = JacksonUtils.json2pojo(json, User.class);
            System.out.println("json-serialization get : " + String.valueOf(System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fastjsonSerialization(String key, User bean) {

        try {
            long start = System.currentTimeMillis();
            String json = JSON.toJSONString(bean);

            manager.set(key, json);
            System.out.println("fastjson-serialization set : " + String.valueOf(System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            String json2 = (String) manager.get(key);
            User user = JSON.parseObject(json, User.class);
            System.out.println("fastjson-serialization get : " + String.valueOf(System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hessianSerialization() throws Exception {
        long start = System.currentTimeMillis();
        String json = JacksonUtils.obj2json(bean);
        manager.set("json-serialization", json);
        System.out.println("json-serialization set : " + String.valueOf(System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        String json2 = (String) manager.get("json-serialization");
        User user = JacksonUtils.json2pojo(json, User.class);
        System.out.println("json-serialization get : " + String.valueOf(System.currentTimeMillis() - start));

    }


}
