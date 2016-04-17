package com.kxw.singleton;


/**
 * <a href='http://www.importnew.com/17609.html'>@link</a>
 */
public class Singleton {
    //1.私有化构造器
    private Singleton() {
    }

    //2.单例缓存者，惰性初始化，第一次使用时初始化
    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    //3.提供全局访问点
    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    //4.提供一个计数器来验证一个ClassLoader一个实例
    private int counter = 0;
}


/**
 * 以上定义个了个单例类，首先要私有化类构造器；其次使用InstanceHolder静态内部类持有单例对象，这样可以得到惰性初始化好处；
 * 最后提供全局访问点getInstance，使得需要该单例实例的对象能获取到；
 * 我们在此还提供了一个counter计数器来验证一个ClassLoader一个实例。
 */