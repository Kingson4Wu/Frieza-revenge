package com.kxw.composite.abstractExtends;

import java.util.Iterator;  

public abstract class Equipment {  
  
    private String name;  
  
    public Equipment(String name) {  
        this.name = name;  
    }  
  
    // 实体价格  
    public abstract double netPrice();  
  
    // 折扣价格  
    public abstract double discountPrice();  
  
    // 增加部件的方法  
    public boolean add(Equipment equipment) {  
        return false;  
    }  
  
    // 移除部件方法  
    public boolean remove(Equipment equipment) {  
        return false;  
    }  
  
    // 组合体内访问各个部件的方法.  
    public Iterator iter() {  
        return null;  
    }  
  
} 