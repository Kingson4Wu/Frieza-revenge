package com.kxw.asm.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ASMAOPTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
        InstantiationException, NoSuchMethodException, InvocationTargetException {
        ASMClassLoader asmClassLoader = new ASMClassLoader();
        //TestBean testBean = (TestBean)asmClassLoader.loadClass(TestBean.class.getName()).newInstance();

        Class<?> testBeanNewClass = asmClassLoader.loadClass("com.kxw.asm.aop.TestBean");
        Object testBean = testBeanNewClass.newInstance();
        Method method = testBeanNewClass.getClass().getDeclaredMethod("halloAop");
        method.invoke(testBean);


       /* TestBean testBean = (TestBean)asmClassLoader.loadClass("com.kxw.asm.aop.TestBean").newInstance();
        testBean.halloAop();*/

       //Exception in thread "main" java.lang.ClassCircularityError: com/kxw/asm/aop/TestBean_Tmp
        //暂不知怎么解决



    }
}
