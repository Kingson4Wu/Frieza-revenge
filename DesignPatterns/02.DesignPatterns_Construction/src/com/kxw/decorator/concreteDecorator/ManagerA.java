package com.kxw.decorator.concreteDecorator;

import com.kxw.decorator.component.Project;
import com.kxw.decorator.decorator.Manager;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-3 13:45:18 
* 具体的项目经理A 
*/ 
public class ManagerA extends Manager{ 

    public ManagerA(Project project) { 
        super(project); 
    } 

    /** 
     * 项目经理自己的事情：做早期工作 
     */ 
    @Override
    public void doEarlyWork() {
        System.out.println("项目经理A 在做需求分析"); 
        System.out.println("项目经理A 在做架构设计"); 
        System.out.println("项目经理A 在做详细设计"); 
    } 
}