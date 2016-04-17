package com.kxw.chainOfResponsibility.impure.concreteHandler;

import com.kxw.chainOfResponsibility.impure.handler.Handler;

public class GroupLeader extends Handler {  
	  
    @Override  
    public int approve(double day,int signCount) {  
        if(day<7){  
        	
            System.out.println("小组长审批通过");  
            System.out.println("小组长传给了他的上司"); 
            return getHandler().approve(day,++signCount); 
        }else {  
            System.out.println("小组长审批不通过");  
            return signCount;
        }  
    }  
  
  
}  