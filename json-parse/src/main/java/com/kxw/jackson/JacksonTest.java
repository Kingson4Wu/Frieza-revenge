package com.kxw.jackson;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

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
