package com.kxw.factoryMethod.realFactory;

import com.kxw.factoryMethod.abstractFactory.CarFactory;
import com.kxw.factoryMethod.abstractProduct.Car;
import com.kxw.factoryMethod.realProduct.Benz;

public class BenzFactory implements CarFactory{  
	  
    @Override  
    public Car createCar() {  
        return new Benz();  
    }  
  
}  