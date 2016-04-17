package com.kxw.builder2.abstactBuilder;

import java.util.HashMap;
import java.util.Map;

import com.kxw.builder2.bean.Bus;
import com.kxw.builder2.bean.Car;


/**
 * 导演类,返回请求对象
 *
 * @author king
 */
public class Director {

    private Builder builder = new ConcreteBuilder();

    Object getProductOfBus() {
        Map attributeMap = new HashMap();
        attributeMap.put("name", "bus");
        builder.setProduct(Bus.class, attributeMap);
        Object obj = builder.getProduct();
        return obj;
    }

    Object getProductOfCar() {
        Map attributeMap = new HashMap();
        attributeMap.put("name", "car");
        builder.setProduct(Car.class, attributeMap);
        Object obj = builder.getProduct();
        return obj;
    }
}  