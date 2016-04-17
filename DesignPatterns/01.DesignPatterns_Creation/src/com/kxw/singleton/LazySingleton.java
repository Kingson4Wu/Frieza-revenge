package com.kxw.singleton;

/**
 * 【描述】：懒汉式单例模式
 */
public class LazySingleton {

    //创建实例
    private static LazySingleton singleton = null;

    //私有构造子
    private LazySingleton() {
    }  //构造方法声明为私有，这样就保证了只能有一个对象存在

    //静态工厂方法
    synchronized public static LazySingleton getInstance() {
//如果为空就new一个实例  
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

}  