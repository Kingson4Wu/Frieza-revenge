package com.kxw.singleton;

/**
 * 【描述】：饿汉式单例模式
 */
public class HungrySingleton {

    //创建实例
    private static final HungrySingleton singleton = new HungrySingleton();

    //私有构造子
    private HungrySingleton() {
    }  //构造方法声明为私有，这样就保证了只能有一个对象存在

    //静态工厂方法
    public static HungrySingleton getInstance() {
        return singleton;
    }

}
