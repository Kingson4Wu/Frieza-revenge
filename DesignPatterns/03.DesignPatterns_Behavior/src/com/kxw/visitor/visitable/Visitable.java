package com.kxw.visitor.visitable;

import com.kxw.visitor.visitor.Visitor;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-13下午11:31:46 
 *描述：抽象角色元素 
 */  
public interface Visitable {  
      
    public void accept(Visitor v);  
  
}  