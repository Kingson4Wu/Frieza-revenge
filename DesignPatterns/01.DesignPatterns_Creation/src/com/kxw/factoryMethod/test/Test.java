package com.kxw.factoryMethod.test;

import com.kxw.factoryMethod.abstractFactory.CarFactory;
import com.kxw.factoryMethod.abstractProduct.Car;
import com.kxw.factoryMethod.realFactory.BenzFactory;
import com.kxw.factoryMethod.realFactory.BmwFactory;

public class Test {
	 public static void main(String[] args) {  
	        CarFactory carFactory=new BenzFactory();  
	        Car car=carFactory.createCar();  
	        System.out.println(car.getName()); 
	        
	        
	        CarFactory carFactory2=new BmwFactory();  
	        Car car2=carFactory2.createCar();  
	        System.out.println(car2.getName());  
	        
	        
	    }  
	}  