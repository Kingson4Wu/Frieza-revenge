package com.kxw.classLoader.diffClassloader;


import java.net.URL;
import java.net.URLClassLoader;

public class KingsonClassLoader extends URLClassLoader {

    public KingsonClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            if ("com.kxw.classLoader.diffClassloader.Factory".equals(name)
                    || "com.kxw.classLoader.diffClassloader.Car".equals(name)
                    || "com.kxw.classLoader.diffClassloader.King".equals(name)
                    || "com.kxw.classLoader.diffClassloader.BMW".equals(name)) {
                Class<?> clazz = findLoadedClass(name);
                if (clazz == null) {
                    clazz = findClass(name);
                }
                return clazz;
            }
        }
        return super.loadClass(name);
    }
}

