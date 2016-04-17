package com.kxw.properties.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by kingsonwu on 15/12/13.
 */
public class TestConfig {


    public static void main(String[] args) {
        Config conf = ConfigFactory.load();
        int bar1 = conf.getInt("foo.bar");
        Config foo = conf.getConfig("foo");
        int bar2 = foo.getInt("bar");

        System.out.println("bar1 :" + bar1 + ",bar2 :" + bar2 );
    }
}
