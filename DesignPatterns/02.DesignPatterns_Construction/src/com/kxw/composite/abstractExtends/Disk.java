package com.kxw.composite.abstractExtends;

public class Disk extends Equipment {  
	  
    // 硬盘实体价格  
    public static double diskNetPrice = 2.0;  
  
    // 硬盘折扣价格  
    public static double diskDiscountPrice = 1.0;  
  
    public Disk(String name) {  
        super(name);  
    }  
  
    @Override  
    public double netPrice() {  
        return diskNetPrice;  
    }  
  
    @Override  
    public double discountPrice() {  
        return diskDiscountPrice;  
    }  
  
}  