package com.kxw.bridge.concreteImplementor;

import com.kxw.bridge.implementor.Transport;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-2上午7:41:00 
 *描述：货车 
 */  
public class Goods implements Transport{  
  
    @Override  
    public void transport() {  
          
        System.out.println("运货");  
    }  
  
}  
 