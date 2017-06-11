package com.kxw.composite.abstractExtends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class CompositeEquipment extends Equipment {

    private int i = 0;

    // 定义一个List,用来保存组合体内的各个子对象.  
    private List<Equipment> equipment = new ArrayList<Equipment>();

    public CompositeEquipment(String name) {
        super(name);
    }

    @Override
    public boolean add(Equipment equipment) {
        if (equipment instanceof Disk && this instanceof Chassis) {
            System.out.println("在盘盒里面放了一个硬盘");
        } else if (equipment instanceof Chassis && this instanceof Cabinet) {
            System.out.println("在柜子里面放了一个盘盒");
        }
        this.equipment.add(equipment);
        return true;
    }

    @Override
    public double netPrice() {
        double netPrice = 0.;
        if (this instanceof Cabinet) {
            System.out.println("我是在柜子的组合对象里面.柜子本身价格为:"
                    + Cabinet.cabinetNetPrice);
        } else if (this instanceof Chassis) {
            System.out.println("我是在盘盒的组合对象里面.盘盒本身价格为:"
                    + Chassis.chassisNetPrice);
        }
        Iterator<Equipment> iter = equipment.iterator();
        while (iter.hasNext()) {
            Equipment equipment = (Equipment) iter.next();
            if (equipment instanceof Chassis) {
                System.out.println("在柜子里面发现一个盘盒，计算它的价格");
            } else if (equipment instanceof Disk) {
                System.out.println("在盘盒里面发现一个硬盘，计算它的价格");
                System.out.println("硬盘本身价格为:" + Disk.diskNetPrice);
            }
            netPrice += equipment.netPrice();
        }
        return netPrice;
    }

    @Override
    public double discountPrice() {
        double discountPrice = 0.;
        Iterator<Equipment> iter = equipment.iterator();
        while (iter.hasNext()) {
            discountPrice += ((Equipment) iter.next()).discountPrice();
        }
        return discountPrice;
    }

    // 这里提供用于访问自己组合体内的部件方法。  
    // 上面Disk之所以没有，是因为Disk是个单独(Primitive)的元素.  
    @Override
    public Iterator iter() {
        return equipment.iterator();
    }

    // 重载Iterator方法  
    public boolean hasNext() {
        return i < equipment.size();
    }

    // 重载Iterator方法  
    public Object next() {
        if (hasNext()) {
            return equipment.get(i++);
        } else {
            throw new NoSuchElementException();
        }
    }

}  