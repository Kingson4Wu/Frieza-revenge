package com.kxw.test;

/**
 * <a href='http://www.importnew.com/13859.html'>@link</a>
 */
public class ConditionTest {

    public static void main(String[] args) {
        Object o1 = true ? new Integer(1) : new Double(2.0);
        Object o2;

        if (true)
            o2 = new Integer(1);
        else
            o2 = new Double(2.0);

        System.out.println(o1);//1.0
        System.out.println(o2);//1

        //哦！如果『需要』，条件运算符会做数值类型的类型提升，这个『需要』有非常非常非常强的引号。
        // 因为，你觉得下面的程序会抛出NullPointerException吗？

        Integer i = new Integer(1);
        if (i.equals(1))
            i = null;
        Double d = new Double(2.0);
        Object o = true ? i : d; // NullPointerException!
        System.out.println(o);
    }


}
