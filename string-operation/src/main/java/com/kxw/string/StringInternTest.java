package com.kxw.string;

/**
 * Created by kingsonwu on 17/2/4.
 */
public class StringInternTest {


    /**
     * <a href='http://txy821.iteye.com/blog/760957'>@link</a>
     * JDK的api文档是这么解释的：
     =======================================================================
     返回字符串对象的规范化表示形式。
     一个初始时为空的字符串池，它由类 String 私有地维护。
     当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（该对象由 equals(Object) 方法确定），则返回池中的字符串。否则，将此 String 对象添加到池中，并且返回此 String 对象的引用。
     它遵循对于任何两个字符串 s 和 t，当且仅当 s.equals(t) 为 true 时，s.intern() == t.intern() 才为 true。
     所有字面值字符串和字符串赋值常量表达式都是内部的。
     * @param args
     */
    public static void main(String[] args) {
        String a = "kxw";
        String b = new String("kxw");
        String c = "kxw";
        String d = new String("kxw");
        //String e = a.intern();
        String e = b.intern();

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == d);

        System.out.println("------");
        System.out.println(a == e);
        System.out.println(b == e);
        System.out.println(d == e);

        System.out.println("------");

        String aa = new String("kingson");

        //new String() 创建对象不入池
        //赋值包含变量不入池
    }

    /**
     * 字符串字面池指的是常量池.

     字符串对象的创建方式有两种

     如下:

     String s1 = new String("");   //第一种

     String s2 = "";               //第二种

     第一种始终不会入池的.

     第二种要看情况而定(等号右边如果是常量则入池,非常量则不入池)

     例:

     String s3 = "a" + "b"; //"a"是常量,"b"是常量,常量+常量=常量,所以会入池.

     String s4 = s1 + "b";   //s1是变量,"b"是常量,变量+常量!=常量,所以不会入池.

     一旦入池的话,就会先查找池中有无此对象.如果有此对象,则让对象引用指向此对象;如果无此对象,则先创建此对象,再让对象引用指向此对象.

     例:

     String s5 = "abc"; //先在池中查找有无"abc"对象,如果有,则让s5指向此对象;如果池中无"abc"对象,则在池中创建一个"abc"对象,然后让s5指向该对象.
     补充一下：

     看了字节码后，发现
     String str ="a"+"b";
     完全等同于
     String str="ab";
     */
}




