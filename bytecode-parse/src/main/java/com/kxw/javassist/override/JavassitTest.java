package com.kxw.javassist.override;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * {<a href='http://blog.csdn.net/houpengfei111/article/details/8593846'>@link</a>}
 */
public class JavassitTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException{
        CtClass ctClass=ClassPool.getDefault().get("com.kxw.javassist.override.Demo");
        String oldName="forJavassistTest";
        CtMethod ctMethod=ctClass.getDeclaredMethod(oldName);
        /*StringBuffer sb=new StringBuffer();
        sb.append("{System.out.println(\"22222222\");\n")
                //.append(ctMethod.getMethodInfo().getCodeAttribute().+"($$);\n") //不知如何获取方法体？？
                .append("System.out.println(\"11111111111\");\n}");
        ctMethod.setBody(sb.toString());*/
        ctMethod.insertBefore("System.out.println(\"22222222\");\n");
        ctMethod.insertAfter("System.out.println(\"11111111111\");\n");
        //增加新方法
        //ctClass.addMethod(newMethod);
        //类已经更改，注意不能使用A a=new A();，因为在同一个classloader中，不允许装载同一个类两次
        Demo a=(Demo)ctClass.toClass().newInstance();
        a.forJavassistTest();

        try {
            ctClass.writeFile("e:\\kxw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
