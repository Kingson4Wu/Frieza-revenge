package com.kxw.composite.abstractExtends;

public class Client {

    public static void main(String[] args) {

        Cabinet cabinet = new Cabinet("柜子");

        Chassis chassis = new Chassis("盘盒");

        // 将盘盒装到箱子里  
        cabinet.add(chassis);

        // 将硬盘装到盘盒里  
        chassis.add(new Disk("硬盘"));

        // 查询整个柜子的实体价格  
        System.out.println("整个柜子的实体价格(包括里面的盘盒和硬盘) =" + cabinet.netPrice());

        // 查询整个柜子的折扣价格  
        System.out.println("整个柜子的折扣价格(包括里面的盘盒和硬盘) =" + cabinet.discountPrice());
    }
}  