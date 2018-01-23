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
        Prototype p = new ConcretePrototype();
        c.prototype = c.getNewPrototype(p);

        System.out.println(c.prototype == p);

        if (c.prototype instanceof ConcretePrototype && p instanceof ConcretePrototype) {
            System.out.println(((ConcretePrototype)c.prototype).getName() == ((ConcretePrototype)p).getName());
            System.out.println(((ConcretePrototype)c.prototype).getName());
            System.out.println(((ConcretePrototype)c.prototype).getList() == ((ConcretePrototype)p).getList());
            System.out.println(((ConcretePrototype)p).getList());
        }

    }

    /**
     * @param prototype
     * @return
     */
    public Prototype getNewPrototype(Prototype prototype) {
        return (Prototype)prototype.clone();
    }
}  