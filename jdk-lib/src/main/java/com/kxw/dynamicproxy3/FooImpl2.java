package com.kxw.dynamicproxy3;

public class FooImpl2 implements Foo
{
    public FooImpl2()
    {
    }

    @Override
    public void doAction()
    {
        System.out.println("in FooImp2.doAction()");
    }

}
