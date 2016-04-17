package com.kxw.java8.streams;

/**
 * Created by kingsonwu on 16/2/7.
 */
public class Employee {

    String name;
    String city;
    int scales;


    public Employee(String name, String city, int scales) {
        this.name = name;
        this.city = city;
        this.scales = scales;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getScales() {
        return scales;
    }

    public void setScales(int scales) {
        this.scales = scales;
    }
}
