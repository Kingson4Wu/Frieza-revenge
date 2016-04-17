package com.kxw.visitor.visitor;

import com.kxw.visitor.concreteElement.ConcreteElementA;
import com.kxw.visitor.concreteElement.ConcreteElementB;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-13下午11:31:28 
 *描述：抽象访问者 
 */  
public interface Visitor {  
  
    public void visit(ConcreteElementB able );  
    public void visit(ConcreteElementA able );  
}  