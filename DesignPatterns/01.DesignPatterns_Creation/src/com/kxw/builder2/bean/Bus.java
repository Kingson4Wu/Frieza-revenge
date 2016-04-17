package com.kxw.builder2.bean;

/**
 * 公共汽车对象
 *
 * @author king
 */
public class Bus {

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
        System.out.println("Bus名: " + this.getName() + "--Bus类型: " + this.getType());
        return null;
    }

} 