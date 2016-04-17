package com.google.guava.reflection;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <a href='http://ifeve.com/guava-reflection/'>@link</a>
 */
public class TestReflection {

    @Test
    public void test(){
        //由于类型擦除，你不能够在运行时传递泛型类对象——你可能想强制转换它们，并假装这些对象是有泛型的，但实际上它们没有。
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
        //returns true, even though ArrayList<String> is not assignable from ArrayList<Integer>

        /**
         * 背景：类型擦除与反射
         Java不能在运行时保留对象的泛型类型信息。如果你在运行时有一个ArrayList<String>对象，
         你不能够判定这个对象是有泛型类型ArrayList<String>的 —— 并且通过不安全的原始类型，
         你可以将这个对象强制转换成ArrayList<Object>。
         但是，反射允许你去检测方法和类的泛型类型。如果你实现了一个返回List的方法，并且你用反射获得了这个方法的返回类型，
         你会获得代表List<String>的ParameterizedType。
         TypeToken类使用这种变通的方法以最小的语法开销去支持泛型类型的操作。
         */

        //获取一个基本的、原始类的TypeToken非常简单
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        //为获得一个含有泛型的类型的TypeToken —— 当你知道在编译时的泛型参数类型 —— 你使用一个空的匿名内部类
        TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {};
        //或者你想故意指向一个通配符类型：
        TypeToken<Map<?, ?>> wildMapTok = new TypeToken<Map<?, ?>>() {};
        //TypeToken提供了一种方法来动态的解决泛型类型参数

        //....
    }

    @Test
    public void testClassPath() throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        ClassPath classpath = ClassPath.from(classloader); // scans the class path used by classloader
       /* for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses("com.google.guava.basic")) {
            System.out.println(classInfo.getName());
        }*/

        /*for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses()) {
            System.out.println(classInfo.getName());
        }*/
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClassesRecursive("com.google.guava.basic")) {
            System.out.println(classInfo.getName());
        }
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses("apache.commons.lang")) {
            System.out.println(classInfo.getName());
        }
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses("com.kxw.bean")) {
            System.out.println(classInfo.getName());
            classInfo.load();
        }

        /**
         * ClassInfo是被加载类的句柄。它允许程序员去检查类的名字和包的名字，让类直到需要的时候才被加载。
         值得注意的是，ClassPath是一个尽力而为的工具。它只扫描jar文件中或者某个文件目录下的class文件。
         也不能扫描非URLClassLoader的自定义class loader管理的class，所以不要将它用于关键任务生产任务。
         */
    }

}
