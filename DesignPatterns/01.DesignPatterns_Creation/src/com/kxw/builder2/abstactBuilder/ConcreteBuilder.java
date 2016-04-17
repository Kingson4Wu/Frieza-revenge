package com.kxw.builder2.abstactBuilder;

import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * 具体建造者类
 *
 * @author king
 */
public class ConcreteBuilder extends Builder {
    private Object obj = null;

    //用反射的方法得到实例化的产品  
    void setProduct(Class clazz, Map setMap) {
        try {
            obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                String stringLetter = fieldName.substring(0, 1).toUpperCase();
                String setName = "set" + stringLetter + fieldName.substring(1);
                if (setMap.containsKey(fieldName)) {
                    Method setMethod = clazz.getMethod(setName,
                            new Class[]{field.getType()});
                    setMethod.invoke(obj,
                            new Object[]{setMap.get(fieldName)});
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    Object getProduct() {
        return obj;
    }
}  
