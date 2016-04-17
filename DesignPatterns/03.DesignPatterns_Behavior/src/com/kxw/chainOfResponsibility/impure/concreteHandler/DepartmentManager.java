package com.kxw.chainOfResponsibility.impure.concreteHandler;

import com.kxw.chainOfResponsibility.impure.handler.Handler;

public class DepartmentManager extends Handler {  
	  
   
  
    @Override  
    public int approve(double day,int signCount) {  
        if(day<5){  
        	
            System.out.println("部门经理审批通过");  
            System.out.println("部门经理传给了他的上司"); 
            return getHandler().approve(day,++signCount); 
        }else {  
            System.out.println("部门经理审批不通过");  
            return signCount;
        }  
    }  
    
    
}  