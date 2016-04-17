package com.kxw.singleton;

/**
 * 【描述】：懒汉式单例模式
 */
public class LazySingletonSynchronized {

    //创建实例
    private static LazySingletonSynchronized singleton = null;

    //私有构造子
    private LazySingletonSynchronized() {
    }  //构造方法声明为私有，这样就保证了只能有一个对象存在

    //静态工厂方法
    public static LazySingletonSynchronized getInstance() {
//如果为空就new一个实例  
        //锁加在这里
        synchronized (LazySingleton.class) {
            if (null == singleton) {
                singleton = new LazySingletonSynchronized();
            }
        }
        return singleton;
    }

}