package com.kxw.templateMethod.concreteTemplate;

import com.kxw.templateMethod.abstractTemplate.AbstractClass;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-9下午8:57:50 
 *描述：除法 
 */  
public class DivClass extends AbstractClass{  
  
    public DivClass(int num1, int num2) {
		super(num1, num2);
		// TODO Auto-generated constructor stub
	}

	@Override  
    public int operate(int m,int n) {  
		System.out.println(m+"/"+n);
        return m/n;  
    }  
  
}  