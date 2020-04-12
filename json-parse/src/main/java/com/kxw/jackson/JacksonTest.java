package com.kxw.jackson;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

    // 数组格式返回{},jackson报错，fastjson不报错，返回一个对象属性为空，size＝1的list

    public static void main(String[] args) {

        String str = "xxx";

        try {
            new ObjectMapper().readValue(str, Example.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class Example{
        private int test;
        public int getTest() {
            return test;
        }
        public void setTest(int test) {
            this.test = test;
        }
    }
}
