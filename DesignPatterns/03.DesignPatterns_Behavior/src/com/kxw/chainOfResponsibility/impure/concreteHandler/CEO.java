package com.kxw.chainOfResponsibility.impure.concreteHandler;

import com.kxw.chainOfResponsibility.impure.handler.Handler;


public class CEO extends Handler {  

	@Override  
    public int approve(double day,int signCount) {  
        if(day<4.5){  
        	
            System.out.println("CEO审批通过");  
            return ++signCount; 
        }else {  
            System.out.println("CEO审批不通过");  
            return signCount;
        }  
    }  

}  