package com.kxw.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kxw.gson.bean.Args;
import com.kxw.gson.bean.SendByUserIds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kingsonwu on 15/12/25.
 */
public class TestJson {

    public static void main(String[] args) {


        SendByUserIds sendByUserIds=new SendByUserIds();
        List<String> list=new ArrayList<String>();
        sendByUserIds.setUserIds(list);
        Args args2=new Args();
        sendByUserIds.setArgs(args2);

        sendByUserIds.getUserIds().add("000676");
        sendByUserIds.getUserIds().add("000643");
        sendByUserIds.getUserIds().add("000653");
        sendByUserIds.getUserIds().add("000876");

        sendByUserIds.setAppName("Arsenal,Kaka',22,Kingson");
        sendByUserIds.setContent("托雷斯");
        sendByUserIds.setMessageType(2);
        sendByUserIds.setTitle("切尔西");

        sendByUserIds.setExpiredTime( new Date());
        sendByUserIds.getArgs().setType(4);
        sendByUserIds.getArgs().setValue("Messi");

        //生成Gson对象，把对象及其属性对象转换为Json字符串，
        Gson g = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        String jsonResult = g.toJson(sendByUserIds);
        System.out.println(jsonResult);

    }


    /**
     * http://blog.csdn.net/king87130/article/details/8009923
     * 生成Gson对象，把对象及其属性对象转换为Json字符串，
     * 解决子属性对象的无限递归问题，只转换我们用标签暴露的对象类型
     Gson g = new GsonBuilder()
     .excludeFieldsWithoutExposeAnnotation()
     .serializeNulls()
     .setDateFormat(DateFormat.LONG)
     .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
     .setPrettyPrinting()
     .setVersion(1.0)
     .create();

     String jsonResult = g.toJson(sysUser.getPageResult());
     System.out.println(jsonResult);

     response.setContentType("application/json;charset=utf-8");
     response.setHeader("Cache-Control", "no-cache");

     PrintWriter out = response.getWriter();
     out.print(jsonResult);
     out.flush();
     out.close();
     */
}