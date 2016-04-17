package com.kxw.visitor.concreteVisitor;

import com.kxw.visitor.concreteElement.ConcreteElementA;
import com.kxw.visitor.concreteElement.ConcreteElementB;
import com.kxw.visitor.visitor.Visitor;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-13下午11:32:55 
 *描述：具体访问者B 
 */  
public class ConcreteVisitorB implements Visitor{  
  
    @Override  
    public void visit(ConcreteElementB able) {  
    	System.out.println("Visitor B:");
        able.operate();  
    }  
  
    @Override  
    public void visit(ConcreteElementA able) {  
    	System.out.println("Visitor B:");
        able.operate();  
    }  
  
      
  
}  