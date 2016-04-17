package com.kxw.aop;


/**
 * <a href='http://blog.jamespan.me/2015/08/31/capture-perf-data-with-aop/'>@link</a>
 */
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Interceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class clazz = invocation.getMethod().getDeclaringClass();
        String method = invocation.getMethod().getName();
        String mark = clazz.getCanonicalName() + "#" + method;
        //Profiler.enter(mark);
        try {
            return invocation.proceed();
        } finally {
            //String log = Profiler.exit();
            /*if (log != null) {
                System.out.println(log);
            }*/
        }
    }
}