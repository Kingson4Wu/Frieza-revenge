package com.kxw.composite.abstractExtends;

public class Chassis extends CompositeEquipment {  
	  
    public static double chassisNetPrice = 2.0;  
    public static double chassisDiscountPrice = 1.0;  
  
    public Chassis(String name) {  
        super(name);  
    }  
  
    // 盒子的价格以及盒子里面硬盘价格.  
    @Override
    public double netPrice() {
        return chassisNetPrice + super.netPrice();  
    }  
  
    //  
    @Override
    public double discountPrice() {
        return chassisDiscountPrice + super.discountPrice();  
    }  
}  