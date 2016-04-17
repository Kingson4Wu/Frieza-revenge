package com.kxw.abstractFactory.realFactory;

import com.kxw.abstractFactory.RealProduct.BenzBusinessCar;
import com.kxw.abstractFactory.RealProduct.BmwBusinessCar;
import com.kxw.abstractFactory.abstractFactory.CarFactory;
import com.kxw.abstractFactory.abstractProduct.BenzCar;
import com.kxw.abstractFactory.abstractProduct.BmwCar;

public class BusinessCarFactory implements CarFactory{  
	  
    @Override  
    public BmwCar createBmwCar() {  
        return new BmwBusinessCar();  
    }  
  
    @Override  
    public BenzCar createBenzCar() {  
        return new BenzBusinessCar();  
    }  
  
}  