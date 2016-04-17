package com.kxw.java8.function;

/**
 * {<a href='http://www.jb51.net/article/48304.htm'>@link</a>}
 */
public class TestFunctional {

    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        /**
         * 需要注意如果@FunctionalInterface如果没有指定，上面的代码也是对的。
         译者注 将lambda表达式映射到一个单方法的接口上，这种做法在Java 8之前就有别的语言实现，
         比如Rhino JavaScript解释器，如果一个函数参数接收一个单方法的接口而你传递的是一个function，
         Rhino 解释器会自动做一个单接口的实例到function的适配器，
         典型的应用场景有 org.w3c.dom.events.EventTarget 的addEventListener 第二个参数 EventListener。
         */

        //方法与构造函数引用
        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer converted2 = converter2.convert("123");
        System.out.println(converted2);   // 123
        //Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用，上面的代码展示了如何引用一个静态方法，我们也可以引用一个对象的方法：

        Something something = new Something();

        Converter<String, String> converter3 = something::startsWith;
        String converted3 = converter3.convert("Java");
        System.out.println(converted3);    // "J"

        //接下来看看构造函数是如何使用::关键字来引用的
        //我们只需要使用 Person::new 来获取Person类构造函数的引用，
        // Java编译器会自动根据PersonFactory.create方法的签名来选择合适的构造函数。
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

    }

}
