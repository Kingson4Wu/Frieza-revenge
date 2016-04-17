package com.kxw.chainOfResponsibility.impure.test;

import com.kxw.chainOfResponsibility.impure.concreteHandler.CEO;
import com.kxw.chainOfResponsibility.impure.concreteHandler.DepartmentManager;
import com.kxw.chainOfResponsibility.impure.concreteHandler.GroupLeader;
import com.kxw.chainOfResponsibility.impure.concreteHandler.ProjectManager;

/** 
 *  
 *描述：测试类，首先来创建责任链，然后发出请求模拟员工来请假 
 */  
public class Client {  
  
    /** 
     *描述： 
     */  
    public static void main(String[] args) {  
  
        //创建节点  
        GroupLeader gl = new GroupLeader();  
        ProjectManager pm = new ProjectManager();  
        DepartmentManager dm = new DepartmentManager();  
        CEO ceo = new CEO();  
        //建立责任链  
        gl.setHandler(pm);  
        pm.setHandler(dm);  
        dm.setHandler(ceo);  
          
        //向小组长发出申请，请求审批4天的假期  
        int sign=gl.approve(4.5D, 0);  
        if(sign==4){
        	System.out.println("审批通过可以休假了！！！");
        }else{
        	System.out.println("审批失败不可以休假！！！");
        }
          
    
    }  
  
}  