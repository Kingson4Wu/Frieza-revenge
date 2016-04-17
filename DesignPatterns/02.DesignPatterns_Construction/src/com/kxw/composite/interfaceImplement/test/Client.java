package com.kxw.composite.interfaceImplement.test;

import com.kxw.composite.interfaceImplement.composite.Leader;
import com.kxw.composite.interfaceImplement.leaf.Employee;

/**
 * 作者：alaric
 * 时间：2013-7-20下午5:49:37
 * 描述：测试类
 */
public class Client {

    /**
     * 作者：alaric
     * 时间：2013-7-20下午5:49:32
     * 描述：
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub  
        Leader leader1 = new Leader("张三");
        Leader leader2 = new Leader("李四");
        Employee employe1 = new Employee("王五");
        Employee employe2 = new Employee("赵六");
        Employee employe3 = new Employee("陈七");
        Employee employe4 = new Employee("徐八");
        leader1.add(leader2);
        leader1.add(employe1);
        leader1.add(employe2);
        leader2.add(employe3);
        leader2.add(employe4);
        leader1.doSomething();

    }
}