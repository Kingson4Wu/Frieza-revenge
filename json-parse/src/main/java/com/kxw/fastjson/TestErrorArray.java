package com.kxw.fastjson;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class TestErrorArray {

    public static void main(String[] args) {

        //1.2.33 数组格式返回{},jackson报错，fastjson不报错，返回一个对象属性为空，size＝1的list
        B b = JSON.parseObject("{\"list\":{}}",B.class);

        System.out.println(b);

    }


    public static class B{
        List<A> list;

        public List<A> getList() {
            return list;
        }

        public void setList(List<A> list) {
            this.list = list;
        }
    }

    public static class A {

    }
}
