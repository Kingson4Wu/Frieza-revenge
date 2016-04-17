package com.kxw.bridge.refinedAbstraction;

import com.kxw.bridge.abstraction.Vehicle;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-2上午7:45:40 
 *描述：汽车的实现类 
 */  
public class Car extends Vehicle {  
  
    @Override  
    public void transport() {  
  
        System.out.print("汽车");  
        super.transport();  
    }  
  
}  