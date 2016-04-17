package com.kxw.bridge.abstraction;

import com.kxw.bridge.implementor.Transport;

/**
 * 作者：alaric
 * 时间：2013-8-2上午7:41:31
 * 描述：车的抽象类
 */
public abstract class Vehicle {

    private Transport implementor;

    public void transport() {
        implementor.transport();
    }

    public Transport getImplementor() {
        return implementor;
    }

    public void setImplementor(Transport implementor) {
        this.implementor = implementor;
    }

}  