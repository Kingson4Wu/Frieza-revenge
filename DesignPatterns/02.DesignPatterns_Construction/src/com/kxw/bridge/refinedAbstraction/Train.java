package com.kxw.bridge.refinedAbstraction;

import com.kxw.bridge.abstraction.Vehicle;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-2上午7:45:58 
 *描述：火车的实现类 
 */  
public class Train extends Vehicle {  
  
    @Override  
    public void transport() {  
  
        System.out.print("火车");  
        super.transport();  
    }  
  
}  