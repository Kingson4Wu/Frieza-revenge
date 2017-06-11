package com.kxw.proxy;




//代理角色

public class ProxySubject extends Subject

{
    private RealSubject realSubject; // 以真实角色作为代理角色的属性

    public ProxySubject()
    {
    }

    @Override
    public void request() // 该方法封装了真实对象的request方法

    {
        preRequest();

        if (realSubject == null)
        {
            realSubject = new RealSubject();
        }

        realSubject.request(); // 此处执行真实对象的request方法

        postRequest();
    }

    private void preRequest()
    {

        // something you want to do before requesting

    }

    private void postRequest()
    {

        // something you want to do after requesting

    }

}
