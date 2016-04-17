package com.kxw.builder2.bean;

/**
 * 小汽车对象
 *
 * @author king
 */
public class Car {
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        System.out.println("Car名: " + this.getName() + "--Car类型: "
                + this.getType());
        return null;
    }

}  
