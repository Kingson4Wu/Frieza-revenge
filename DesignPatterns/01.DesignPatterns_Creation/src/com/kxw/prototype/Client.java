package com.kxw.prototype;

/**
 * 作者：alaric
 * 时间：2013-7-18下午10:41:14
 * 描述：客户端
 */
public class Client {
    private Prototype prototype;

    /**
     * @param args
     */
    public static void main(String[] args) {
        Client c = new Client();
        c.prototype = c.getNewPrototype(new ConcretePrototype());

    }

    /**
     * @param prototype
     * @return
     */
    public Prototype getNewPrototype(Prototype prototype) {
        return (Prototype) prototype.clone();
    }
}  