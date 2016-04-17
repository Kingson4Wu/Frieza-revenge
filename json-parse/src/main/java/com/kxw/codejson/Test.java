package com.kxw.codejson;

import link.jfire.baseutil.collection.StringCache;
import link.jfire.codejson.JsonObject;
import link.jfire.codejson.JsonTool;
import link.jfire.codejson.function.WriterAdapter;
import link.jfire.codejson.strategy.WriteStrategy;

import java.text.DecimalFormat;

/**
 * {<a href='http://blog.csdn.net/kuangzhanshatian/article/details/49738049'>@link</a>}
 */
public class Test {

    public static void main(String[] args) {

        Home home = new Home();
        home.setPerson(new Person());
        //这样就完成了将home转换为json字符串的动作
        String json = JsonTool.write(home);

        System.out.println(json);

        //这样就完成了将json字符串转换为json对象的动作
        JsonObject jsonObject =(JsonObject)JsonTool.fromString(json);
        //这样就完成了将json字符串转换为java对象的动作
        Home result = JsonTool.read(Home.class,json);

        WriteStrategy strategy = new WriteStrategy();
        //指定一个输出策略，将name这个属性名在输出的时候替换成hello
        home.setName("kxw");
        strategy.addRenameField("name", "hello");
        json = strategy.write(home);

        System.out.println(json);

      /*  strategy = new WriteStrategy();
        //指定一个输出策略，将float输出的时候截止到小数点1位
        strategy.addTypeStrategy(float.class,new WriterAdapter(){
            public void write(float target,StringCache cache){
                DecimalFormat format = new DecimalFormat("##.00");
                cache.append(format.format(target));
            }
        });
        json = strategy.write(home);*/
    }
}
