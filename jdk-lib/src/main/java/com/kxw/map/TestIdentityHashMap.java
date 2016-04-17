package com.kxw.map;

import java.util.*;

/**
 * {<a href='http://tianya23.blog.51cto.com/1081650/707603'>@link</a>}
 *
 * 在使用map的时候，大家肯定会想到key-value，key用于检索value的内容。
 * 在正常情况下，可以不允许重复；但是其实重复在java中分为2中情况，一是内存地址重复，另一个是不同的地址但内容相等，
 * 而IdentityHashMap用于后者，即内容相等。
 * 更详细的解释如下：此类利用哈希表实现 Map 接口，比较键（和值）时使用引用相等性代替对象相等性。
 * 换句话说，在 IdentityHashMap 中，当且仅当 (k1==k2) 时，才认为两个键 k1 和 k2 相等（在正常 Map 实现（如 HashMap）中，
 * 当且仅当满足下列条件时才认为两个键 k1 和 k2 相等：(k1==null ? k2==null : e1.equals(e2))）。
 * 此类不是 通用 Map 实现！此类实现 Map 接口时，它有意违反 Map 的常规协定，该协定在比较对象时强制使用equals 方法。
 * 此类设计仅用于其中需要引用相等性语义的罕见情况。
 */
public class TestIdentityHashMap {

    //HashMap情况：
    /*public static void main(String args[]){
        Map<Person,String> map = null ; // 声明Map对象
        map = new HashMap<Person,String>() ;
        map.put(new Person("张三",30),"zhangsan_1") ; // 加入内容
        map.put(new Person("张三",30),"zhangsan_2") ; // 加入内容
        map.put(new Person("李四",31),"lisi") ;   // 加入内容
        Set<Map.Entry<Person,String>> allSet = null ;   // 准备使用Set接收全部内容
        allSet = map.entrySet() ;
        Iterator<Map.Entry<Person,String>> iter = null ;
        iter = allSet.iterator() ;
        while(iter.hasNext()){
            Map.Entry<Person,String> me = iter.next() ;
            System.out.println(me.getKey() + " --> " + me.getValue()) ;
        }
    }*/

    //IdentityHashMap情况
    public static void main(String args[]){
        Map<Person,String> map = null ; // 声明Map对象
        map = new IdentityHashMap<Person,String>() ;
        map.put(new Person("张三",30),"zhangsan_1") ; // 加入内容
        map.put(new Person("张三",30),"zhangsan_2") ; // 加入内容
        map.put(new Person("李四",31),"lisi") ;   // 加入内容
        Set<Map.Entry<Person,String>> allSet = null ;   // 准备使用Set接收全部内容
        allSet = map.entrySet() ;
        Iterator<Map.Entry<Person,String>> iter = null ;
        iter = allSet.iterator() ;
        while(iter.hasNext()){
            Map.Entry<Person,String> me = iter.next() ;
            System.out.println(me.getKey() + " --> " + me.getValue()) ;
        }
    }
}
