package com.kxw.decorator.concreteDecorator;

import com.kxw.decorator.component.Project;
import com.kxw.decorator.decorator.Manager;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-3 13:45:27 
* 具体的项目经理B 
*/ 
public class ManagerB extends Manager { 

    public ManagerB(Project project) { 
        super(project); 
    } 

     /** 
     * 项目经理自己的事情：做早期工作 
     */ 
    @Override
    public void doEarlyWork() {
        System.out.println("项目经理B 在做需求分析"); 
        System.out.println("项目经理B 在做详细设计"); 
    } 

    /** 
     * 项目经理做收尾工作 
     */ 
    @Override
    public void doEndWork() {
        System.out.println("项目经理B 在做收尾工作"); 
    } 
}