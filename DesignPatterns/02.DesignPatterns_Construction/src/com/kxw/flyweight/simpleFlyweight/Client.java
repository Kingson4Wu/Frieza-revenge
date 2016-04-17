package com.kxw.flyweight.simpleFlyweight;

public class Client {

    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));//Flyweight对象fly的a参数通过factory传入(它的值应当在享元对象被创建时赋予。所有的内蕴状态在对象创建之后，就不会再改变了),Second Call参数直接通过自身方法传入
        //享元工厂角色类，必须指出的是，客户端不可以直接将具体享元类实例化，而必须通过一个工厂对象，利用一个factory()方法得到享元对象
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }

}
/*
 * 　虽然客户端申请了三个享元对象，但是实际创建的享元对象只有两个，这就是共享的含义。运行结果如下：
 * Intrinsic State = a
Extrinsic State = First Call
Intrinsic State = b
Extrinsic State = Second Call
Intrinsic State = a
Extrinsic State = Third Call
 */
