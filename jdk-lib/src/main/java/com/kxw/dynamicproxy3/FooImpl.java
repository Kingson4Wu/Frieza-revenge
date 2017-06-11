package com.kxw.dynamicproxy3;

public class FooImpl implements Foo
{
    public FooImpl()
    {
    }

    @Override
    public void doAction()
    {
        System.out.println("in FooImp1.doAction()");
    }
}
