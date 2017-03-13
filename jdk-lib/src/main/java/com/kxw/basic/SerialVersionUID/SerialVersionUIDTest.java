package com.kxw.basic.SerialVersionUID;

import java.io.*;

/**
 * Created by kingsonwu on 17/3/12.
 * <a href='http://bbs.csdn.net/topics/392093574'>@link</a>
 */
public class SerialVersionUIDTest {

    public static void main(String[] args) {

        System.out.println(0x211d1ae3);
        System.out.println(0x5dec9293e0874139L);
        //6767945503855821113 跟idea生成的一致 //jdk1.8.0_77 windows
        // jdk1.8.0_77 windows 与 zulu jdk1.8.0_102 windows 一致

        System.out.println(0xe404a21fe304be8aL);
        //-2016308475318714742 跟idea生成的一致 //jdk1.6.0_45 windows


        SerialVersionUIDClass clazz = new SerialVersionUIDClass();
        writeObjectToFile(clazz);

    }

    public static void writeObjectToFile(Object obj) {
        File file = new File("./test1.dat");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }

}