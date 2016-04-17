package com.kxw.Observer.interfaceImplement2;

public class ConcreteWatcher implements Watcher
{

    @Override
    public void update(String str)
    {
        System.out.println(str);
    }

}