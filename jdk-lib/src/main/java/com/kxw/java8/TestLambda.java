package com.kxw.java8;

import com.kxw.java8.function.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kingson.wu on 2015/12/3.
 * {<a href='http://www.jb51.net/article/48304.htm'>@link</a>}
 */
public class TestLambda {

    static int outerStaticNum;
    int outerNum;

    public static void main(String[] args) {

        //首先看看在老版本的Java中是如何排列字符串的：
        //只需要给静态方法 Collections.sort 传入一个List对象以及一个比较器来按指定顺序排列。
        // 通常做法都是创建一个匿名的比较器对象然后将其传递给sort方法。
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        //在Java 8 中你就没必要使用这种传统的匿名对象的方式了，Java 8提供了更简洁的语法，lambda表达式
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        //实际上还可以写得更短
        //对于函数体只有一行代码的，你可以去掉大括号{}以及return关键字，但是你还可以写得更短点
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        //Java编译器可以自动推导出参数类型，所以你可以不用再写一次类型。
        Collections.sort(names, (a, b) -> b.compareTo(a));


        /**
         * Lambda 作用域
         * 在lambda表达式中访问外层作用域和老版本的匿名对象中的方式很相似。你可以直接访问标记了final的外层局部变量，或者实例的字段以及静态变量。
         */
        //访问局部变量
        //我们可以直接在lambda表达式中访问外层的局部变量,但是和匿名对象不同的是，这里的变量num可以不用声明为final，该代码同样正确
        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        stringConverter.convert(2);     // 3
        //不过这里的num必须不可被后面的代码修改（即隐性的具有final的语义）,否则无法编译
        //在lambda表达式中试图修改num同样是不允许的。
        //num = 3;

    }

    //访问对象字段与静态变量
    //和本地变量不同的是，lambda内部对于实例的字段以及静态变量是即可读又可写。该行为和匿名对象是一致的
    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

}

/**

 <a href='http://stackoverflow.com/questions/25055392/lambdas-local-variables-need-final-instance-variables-dont'></a>
 The fundamental difference between a field and a local variable is that the local variable is copied when JVM creates a lambda instance. On the other hand, fields can be changed freely, because the changes to them are propagated to the outside class instance as well (their scope is the whole outside class, as Boris pointed out below).
 The easiest way of thinking about anonymous classes, closures and labmdas is from the variable scope perspective; imagine a copy constructor added for all local variables you pass to a closure.

 <a href='https://www.zhihu.com/question/21395848'></>
 Java编译器支持了闭包，但支持地不完整。说支持了闭包，是因为编译器编译的时候其实悄悄对函数做了手脚，偷偷把外部环境方法的x和y局部变量，拷贝了一份到匿名内部类里。
 Java编译器实现的只是capture-by-value，并没有实现capture-by-reference。
 而只有后者才能保持匿名内部类和外部环境局部变量保持同步。
 但Java又不肯明说，只能粗暴地一刀切，就说既然内外不能同步，那就不许大家改外围的局部变量。
 */