package com.kxw.classLoader;

/**
 * Created by kingsonwu on 16/3/28.
 */
public class TestClassForName {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.kxw.ClassLoader.K");
        //用class for name是为了通过加载类的方式执行静态块的代码,而不必用new去实例化一个对象
        //mysql的驱动就是这样去执行静态块代码的时候完成驱动的注册
    }
}


class K{

    static {
        System.out.println("ddd");
    }
}