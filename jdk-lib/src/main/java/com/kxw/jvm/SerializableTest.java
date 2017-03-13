package com.kxw.jvm;


import sun.tools.serialver.SerialVer;

import java.io.Serializable;

public class SerializableTest implements Serializable{

    public static void main(String[] args) throws Exception {
       /* Object obj = Y.class.newInstance();
        Field field = Y.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        System.out.println(field.getLong(obj));*/

        SerialVer.main(new String[]{"com.kxw.jvm.$Y"});
    }
}
class Y implements Serializable{

    //private static final long serialVersionUID = 1092141947575795242L;

    void prt(){
        System.out.println("Y");
    }
}