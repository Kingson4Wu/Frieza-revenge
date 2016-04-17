package com.kxw.jmx;

/**
 * @author ChenGang 2005-12-3
 * Hello是一个需要被管理的类（普通类）
 */
public class Hello implements HelloMBean {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printHello() {
        System.out.println("Hello World, " + name);
    }
    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);
    }
}            