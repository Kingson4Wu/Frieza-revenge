package com.kxw.dynamicproxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CommonInvocationHandler implements InvocationHandler
{

    // 动态执行对象，需要回调的对象
    private Object target;

    // 支持构造子注射
    public CommonInvocationHandler()
    {

    }

    // 支持构造子注射
    public CommonInvocationHandler(Object target)
    {
        setTarget(target);
    }

    /**
     *
     * 采用setter方法注射
     *
     * @param target
     *
     */
    public void setTarget(Object target)
    {
        this.target = target;
    }

    /**
     *
     * 调用proxy中指定的方法method，并传入参数列表args
     *
     * @param proxy
     *            代理类的类型，例如定义对应method的代理接口
     *
     * @param method
     *            被代理的方法
     *
     * @param args
     *            调用被代理方法的参数
     *
     * @return
     *
     * @throws java.lang.Throwable
     *
     */

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        return method.invoke(target, args);
    }

}