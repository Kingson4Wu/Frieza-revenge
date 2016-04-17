package com.kxw.adapter.inheritance;

/**
 * @author king
 * @version 1.0
 * @Description: 适配类
 */
public class Adapter extends Adaptee implements Target {
    //适配的方法  
    @Override
    public void option() {
        this.adapteeOption();
    }
}  