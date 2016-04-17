package com.kxw.adapter.composition;

/**
 * @author kingson.wu
 * @Description: 适配类
 */
public class Adapter implements Target {

    // 建立一个私有的Adeptee对象
    private Adaptee adaptee = new Adaptee();

    //适配的方法  
    @Override
    public void option() {
        adaptee.adapteeOption();
    }
}  