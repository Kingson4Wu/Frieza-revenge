package com.kxw.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int b, c;
        byte[] buffer = new byte[100];
        File f = new File("c:\\test");
        File fTemp = null;
        try {
            fTemp = File.createTempFile("letter", ".txt", f);
            System.out.println("输入一行文本:");
            b = System.in.read(buffer);
            FileOutputStream writeFile = new FileOutputStream(fTemp);
            writeFile.write(buffer, 0, b);
            FileInputStream In = new FileInputStream(fTemp);
            FileOutputStream Out = new FileOutputStream("c:\\testline.txt");
            while ((c = In.read()) != -1) {
                Out.write(c);
            }
            System.out.println("输入完毕!!");
            writeFile.close();
            In.close();
            Out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            fTemp.deleteOnExit();
        }
    }
}