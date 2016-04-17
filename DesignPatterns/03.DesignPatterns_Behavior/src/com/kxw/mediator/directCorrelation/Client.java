package com.kxw.mediator.directCorrelation;

/**
 * 类A类B通过直接的关联发生关系，假如我们要使用中介者模式，类A类B之间则不可以直接关联，他们之间必须要通过一个中介者来达到关联的目的。
 */
abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //抽象方法，修改数字时同时修改关联对象  
    public abstract void setNumber(int number, AbstractColleague coll);
}

class ColleagueA extends AbstractColleague {
    public void setNumber(int number, AbstractColleague coll) {
        this.number = number;
        coll.setNumber(number * 100);
    }
}

class ColleagueB extends AbstractColleague {

    public void setNumber(int number, AbstractColleague coll) {
        this.number = number;
        coll.setNumber(number / 100);
    }
}

public class Client {
    public static void main(String[] args) {

        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        System.out.println("==========设置A影响B==========");
        collA.setNumber(1288, collB);
        System.out.println("collA的number值：" + collA.getNumber());
        System.out.println("collB的number值：" + collB.getNumber());

        System.out.println("==========设置B影响A==========");
        collB.setNumber(87635, collA);
        System.out.println("collB的number值：" + collB.getNumber());
        System.out.println("collA的number值：" + collA.getNumber());
    }
}  