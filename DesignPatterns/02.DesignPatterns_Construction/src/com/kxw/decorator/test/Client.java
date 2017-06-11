package com.kxw.decorator.test;

import com.kxw.decorator.component.Project;
import com.kxw.decorator.concreteComponent.Employee;
import com.kxw.decorator.concreteDecorator.ManagerA;
import com.kxw.decorator.concreteDecorator.ManagerB;

/**
 * Created by IntelliJ IDEA.
 * User: leizhimin
 * Date: 2008-8-3 13:03:22
 * 客户端测试
 */
public class Client {
    public static void main(String[] args) {
        Project employee = new Employee();        //代码工人 
        Project managerA = new ManagerA(employee); //项目经理 
        Project managerB = new ManagerB(employee); //项目经理 
        //以经理的名义将编码完成，功劳都是经理的，实际编码的是工人 
        managerA.doCoding();
        managerB.doCoding();
    }
}