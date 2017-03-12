package com.kxw.basic.SerialVersionUID;

import java.io.*;

/**
 * Created by kingsonwu on 17/3/12.
 */
public class SerialVersionUIDTest {

    public static void main(String[] args) {

        System.out.println(0x211d1ae3);
        System.out.println(0x6d2e6b78772e6261l);//7867343764202611297

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