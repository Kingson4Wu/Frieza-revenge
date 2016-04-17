package com.kxw.abstractFactory.abstractFactory;

import com.kxw.abstractFactory.abstractProduct.BenzCar;
import com.kxw.abstractFactory.abstractProduct.BmwCar;

/**
 *抽象工厂 
 */

public interface CarFactory {  
    public BmwCar createBmwCar();  
    public BenzCar createBenzCar();  
}  