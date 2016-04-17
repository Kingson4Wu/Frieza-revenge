package com.kxw.visitor.concreteVisitor;

import com.kxw.visitor.concreteElement.ConcreteElementA;
import com.kxw.visitor.concreteElement.ConcreteElementB;
import com.kxw.visitor.visitor.Visitor;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-13下午11:33:29 
 *描述：具体访问者A 
 */  
public class ConcreteVisitorA implements Visitor{  
  
    @Override  
    public void visit(ConcreteElementB able) {  
    	System.out.println("Visitor A:");
        able.operate();  
    }  
  
    @Override  
    public void visit(ConcreteElementA able) {  
    	System.out.println("Visitor A:");
    	able.operate();  
    }  
      
  
}