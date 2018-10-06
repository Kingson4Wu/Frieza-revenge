package com.kxw.cglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

/**
 *
 * javabean与map的转换有很多种方式，比如:
 * 1、通过ObjectMapper先将bean转换为json，再将json转换为map，但是这种方法比较绕，且效率很低，经测试，循环转换10000个bean，就需要12秒！！！不推荐使用
 * 2、通过java反射，获取bean类的属性和值，再转换到map对应的键值对中，这种方法次之，但稍微有点麻烦
 * 3、通过net.sf.cglib.beans.BeanMap类中的方法，这种方式效率极高，它跟第二种方式的区别就是因为使用了缓存，初次创建bean时需要初始化，之后就使用缓存，所以速度极快，经测试，循环bean和map
 * 的转换10000次，仅需要300毫秒左右。
 *
 * ---------------------
 *
 * 本文来自 阿凡驴子 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/q358543781/article/details/50176953?utm_source=copy
 * https://blog.csdn.net/q358543781/article/details/50176953
 */
public class BeanMapConvertUtils {

    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

}
