package com.kxw.cglib;

import java.util.HashMap;
import java.util.Map;

import com.kxw.bean.Kingson;
import net.sf.cglib.beans.BeanMap;

/**
 * CGLIB BeanCopier实现高效拷贝的方法总结:http://focalab.tech/2018/01/30/cglib-beancopier/
 */
public class BeanMapTest {

    public static void main(String args[]) {

        // 初始化
        BeanMap beanMap = BeanMap.create(new Kingson());
        // 构造
        Kingson pojo = new Kingson();
        // 赋值
        beanMap.setBean(pojo);
        // 验证
        System.out.println(beanMap.get("name"));
        System.out.println(beanMap.keySet());
        System.out.println(beanMap.values());

        Map<String, Object>  map = new HashMap<>();
        map.put("name", "kxwkwx");
        beanMap.putAll(map);

        System.out.println(pojo.getName());

    }
}
