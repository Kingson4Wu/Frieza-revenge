package com.kxw.proxy;


//真实角色：实现了Subject的request()方法

public class RealSubject extends Subject
{

    public RealSubject()
    {
    }

    @Override
    public void request()
    {
        System.out.println("From real subject.");
    }

}