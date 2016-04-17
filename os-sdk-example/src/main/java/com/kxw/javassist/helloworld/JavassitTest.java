package com.kxw.javassist.helloworld;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * {<a href='http://blog.csdn.net/houpengfei111/article/details/8593846'>@link</a>}
 */
public class JavassitTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException{
        CtClass ctClass=ClassPool.getDefault().get("com.kxw.javassist.helloworld.Demo");
        String oldName="forJavassistTest";
        CtMethod ctMethod=ctClass.getDeclaredMethod(oldName);
        String newName=oldName+"$impl";
        ctMethod.setName(newName);
        CtMethod newMethod=CtNewMethod.copy(ctMethod,"forJavassistTest",ctClass, null);
        StringBuffer sb=new StringBuffer();
        sb.append("{System.out.println(\"22222222\");\n")
                .append(newName+"($$);\n")
                .append("System.out.println(\"11111111111\");\n}");
        newMethod.setBody(sb.toString());
        //增加新方法
        ctClass.addMethod(newMethod);
        //类已经更改，注意不能使用A a=new A();，因为在同一个classloader中，不允许装载同一个类两次
        Demo a=(Demo)ctClass.toClass().newInstance();
        a.forJavassistTest();
    }
}
/**
 * result:
 * 22222222
 ----------执行方法forJavassistTest()-----------
 11111111111
 */