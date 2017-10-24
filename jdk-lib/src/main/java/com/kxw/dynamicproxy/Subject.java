package com.kxw.dynamicproxy;

//抽象角色(之前是抽象类，此处应改为接口)：

public interface Subject
{
    abstract public void request();

    void response();
}