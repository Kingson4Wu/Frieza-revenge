package com.kxw.cglib;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.core.ReflectUtils;

public class BeanGeneratorTest {

    public static void main(String[] args) {
        BeanGenerator generator = new BeanGenerator();

        generator.addProperty("name", String.class);

        generator.addProperty("id", Integer.class);

        Class clazz = (Class) generator.createClass();

        Object obj = generator.create();

        PropertyDescriptor[] getters = ReflectUtils.getBeanGetters(obj.getClass());

        for (PropertyDescriptor getter : getters) {

            Method write = getter.getWriteMethod();

            System.out.println(write.getName());

        }
    }
}
