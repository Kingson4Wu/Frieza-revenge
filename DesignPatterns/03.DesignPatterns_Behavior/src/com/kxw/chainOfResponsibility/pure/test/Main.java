package com.kxw.chainOfResponsibility.pure.test;

import com.kxw.chainOfResponsibility.pure.concreteHandler.IntermediateProgrammer;
import com.kxw.chainOfResponsibility.pure.concreteHandler.JuniorProgrammer;
import com.kxw.chainOfResponsibility.pure.concreteHandler.SeniorProgrammer;
import com.kxw.chainOfResponsibility.pure.handler.Handler;
import com.kxw.chainOfResponsibility.pure.testor.Testor;

public class Main {  
    
	
    public static void main(String[] args) {  
          
        Handler junior = new JuniorProgrammer();  
        Handler intermediate = new IntermediateProgrammer();  
        Handler senior = new SeniorProgrammer();  
          
        junior.setNextHandler(intermediate);  
        intermediate.setNextHandler(senior);  
          
        //测试人员提bug，苦逼程序员开始改bug喽  
        junior.handleRequest(Testor.giveBug(1));  
        junior.handleRequest(Testor.giveBug(2));  
        junior.handleRequest(Testor.giveBug(3));  
        junior.handleRequest(Testor.giveBug(4));  
    }  
  
}  