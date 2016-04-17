package com.kxw.visitor.concreteElement;

import com.kxw.visitor.visitable.Visitable;
import com.kxw.visitor.visitor.Visitor;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-13下午11:34:02 
 *描述：具体元素A 
 */  
public class ConcreteElementA implements Visitable {  
  
    @Override  
    public void accept(Visitor v) {  
        v.visit(this);  
    }  
  
    public void operate(){  
        System.out.println("ConcreteElementA ....");  
    }  
}  