package com.kxw.bridge.test;

import com.kxw.bridge.concreteImplementor.Goods;
import com.kxw.bridge.concreteImplementor.Guest;
import com.kxw.bridge.refinedAbstraction.Car;
import com.kxw.bridge.refinedAbstraction.Train;

/**
 * 作者：alaric
 * 时间：2013-8-2上午7:45:24
 * 描述：测试类
 */
public class Client {

    /**
     * 作者：alaric
     * 时间：2013-8-2上午7:45:16
     * 描述：
     */
    public static void main(String[] args) {
        //造出一辆火车来  
        Train train = new Train();
        //装入货物  
        train.setImplementor(new Goods());
        //运输  
        train.transport();
        //上客  
        train.setImplementor(new Guest());
        //运输  
        train.transport();


        //造出一汽车来  
        Car car = new Car();
        //装入货物  
        car.setImplementor(new Goods());
        //运输  
        car.transport();
        //上客  
        car.setImplementor(new Guest());
        //运输  
        car.transport();


    }

}  