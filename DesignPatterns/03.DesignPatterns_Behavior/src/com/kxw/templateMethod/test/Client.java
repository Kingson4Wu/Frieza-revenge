package com.kxw.templateMethod.test;

import com.kxw.templateMethod.abstractTemplate.AbstractClass;
import com.kxw.templateMethod.concreteTemplate.AddClass;
import com.kxw.templateMethod.concreteTemplate.DivClass;
import com.kxw.templateMethod.concreteTemplate.MultiClass;
import com.kxw.templateMethod.concreteTemplate.SubClass;


public class Client {  
  
   
    public static void main(String[] args) {  
        //加  
        AbstractClass c1 = new AddClass(1,6);  
        c1.templateMethod();  
        //减  
        AbstractClass c4 = new SubClass(9,5);  
        c4.templateMethod();  
        //乘  
        AbstractClass c2 = new MultiClass(4,3);  
        c2.templateMethod();  
        //除  
        AbstractClass c3 = new DivClass(18,6);  
        c3.templateMethod();  
          
  
    }  
  
}  