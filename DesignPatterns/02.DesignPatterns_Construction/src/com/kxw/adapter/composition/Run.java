package com.kxw.adapter.composition;

public class Run {
    public static void main(String[] args) {
        Target target = new Adapter();
        //看看打印出来啥了
        target.option();
    }
} 