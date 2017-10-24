package com.kxw.dynamicproxy;


public class RealSubject implements Subject
{

    public RealSubject()
    {
    }

    @Override
    public void request()
    {
        System.out.println("From real subject.");
    }

    @Override
    public void response() {
        System.out.println("response.");
    }

}
