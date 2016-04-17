package com.kxw.abstractFactory.realFactory;

import com.kxw.abstractFactory.RealProduct.BenzSportsCar;
import com.kxw.abstractFactory.RealProduct.BmwSportsCar;
import com.kxw.abstractFactory.abstractFactory.CarFactory;
import com.kxw.abstractFactory.abstractProduct.BenzCar;
import com.kxw.abstractFactory.abstractProduct.BmwCar;

public class SportsCarFactory implements CarFactory{  
	  
    @Override  
    public BmwCar createBmwCar() {  
        return new BmwSportsCar();  
    }  
  
    @Override  
    public BenzCar createBenzCar() {  
        return new BenzSportsCar();  
    }  
  
}  