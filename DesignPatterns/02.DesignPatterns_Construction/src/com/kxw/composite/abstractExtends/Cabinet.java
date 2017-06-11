package com.kxw.composite.abstractExtends;

public class Cabinet extends CompositeEquipment {  
	  
    public static double cabinetNetPrice = 10.0;  
    public static double cabinetDiscountPrice = 5.0;  
  
    public Cabinet(String name) {  
        super(name);  
    }  
  
    // 柜子本身价格以及放在柜子里面盒子的价格.  
    @Override
    public double netPrice() {
        return cabinetNetPrice + super.netPrice();  
    }  
  
    @Override
    public double discountPrice() {
        return cabinetDiscountPrice + super.discountPrice();  
    }  
}  
  
  
