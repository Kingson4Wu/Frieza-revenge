package com.kxw.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：alaric
 * 时间：2013-7-18下午10:41:39
 * 描述：实现接口
 */
public class ConcretePrototype implements Prototype, Serializable {

    private String name = "kxw";

    private List<String> list = new ArrayList<>();

    @Override
    public Object clone() {
        try {
            //return super.clone();

            return deepClone();

        } /*catch (CloneNotSupportedException e) {
            e.printStackTrace();

        } */catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return list;
    }
}
