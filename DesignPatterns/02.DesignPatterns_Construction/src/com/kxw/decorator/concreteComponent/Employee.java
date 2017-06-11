package com.kxw.decorator.concreteComponent;

import com.kxw.decorator.component.Project;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-3 12:52:12 
* 代码工人 
*/ 
public class Employee implements Project{ 
    /** 
     * 编码 
     */ 
    @Override
    public void doCoding(){
        System.out.println("代码工人 在编写代码，加班编啊编啊，终于编完了！"); 
    } 
}