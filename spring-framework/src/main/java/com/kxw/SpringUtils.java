package com.kxw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kxw on 2016/1/7.
 */
public class SpringUtils {
    public static void main(String[] args) {
        /// loading spring xml
        ApplicationContext context = new ClassPathXmlApplicationContext("core-init.xml", "spring/spring-daos.xml",
                "spring/spring-datasource.xml");
    }
}
