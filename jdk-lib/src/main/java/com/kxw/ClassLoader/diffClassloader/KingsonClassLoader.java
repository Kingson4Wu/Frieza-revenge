package com.kxw.classLoader.diffClassloader;


import java.io.*;

public class KingsonClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if("com.kxw.classLoader.diffClassloader.B".equals(name)){
            this.findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] bt = loadClassData(name);
        return defineClass(name, bt, 0, bt.length);
    }

    private byte[] loadClassData(String className) {

        String path = Thread.currentThread().getContextClassLoader().getResource("")+ className.replace(".", "/") + ".class";

        // read class
        InputStream is = null;
        try {
            is = new FileInputStream(path.substring(6,path.length()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        // write into byte
        int len = 0;
        try {
            while ((len = is.read()) != -1) {
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // convert into byte array
        return byteSt.toByteArray();
    }
}

