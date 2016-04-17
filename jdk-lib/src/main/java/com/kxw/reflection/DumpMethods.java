package com.kxw.reflection;

import java.lang.reflect.Method;

public class DumpMethods
{
    public static void main(String args[]) throws Exception
    {
        // 加载并初始化命令行参数指定的类
        Class<?> classType = Class.forName(args[0]);
        // 获得类的所有方法
        Method methods[] = classType.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
        {
            System.out.println(methods[i].toString());
        }
    }
}