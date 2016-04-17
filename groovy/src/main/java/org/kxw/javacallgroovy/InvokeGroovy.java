package org.kxw.javacallgroovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

public class InvokeGroovy {
    public static void main(String[] args) {
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {

            String path =InvokeGroovy.class.getResource("").toString().replace("bin", "src");
            path=path.substring(6,path.length());
            @SuppressWarnings("rawtypes")
            Class groovyClass = groovyCl.parseClass(new File(path+"/Foo.groovy"));
            // Class groovyClass = groovyCl.parseClass(new File(InvokeGroovy.class.getResource("org/kxw/javacallgroovy/Foo.groovy").toURI()));
           // IFoo foo = (IFoo) groovyClass.newInstance();
            //System.out.println(foo.run(new Integer(2), "More parameter..."));

            System.out.println("=============================");

            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            System.out.println(groovyObject.invokeMethod("run", new Object[]{new Integer(2),"More parameter..."}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}