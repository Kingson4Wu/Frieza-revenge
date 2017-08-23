package com.kxw.java7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kingsonwu on 17/8/23.
 */
public class TrywithResources {

    /**
     * 在JDK7种提出了try-with-resources机制，
     * 它规定你操作的类只要是实现了AutoCloseable接口就可以在try语句块退出的时候自动调用close 方法关闭流资源。
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (InputStream is = new FileInputStream("/home/biezhi/a.txt");
             OutputStream os = new FileOutputStream("/home/biezhi/b.txt")
        ) {
            char charStr = (char)is.read();
            os.write(charStr);
        }
    }
}
