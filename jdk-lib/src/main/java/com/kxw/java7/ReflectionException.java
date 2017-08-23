package com.kxw.java7;

/**
 * Created by kingsonwu on 17/8/23.
 */
public class ReflectionException {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.biezhi.apple.User");
            clazz.getMethods()[0].invoke(new Object());
        } catch (ReflectiveOperationException e){
            //JDK7修复了这个缺陷，引入了一个新类ReflectiveOperationException可以帮你捕获这些反射异常
            e.printStackTrace();
        }
    }
}
