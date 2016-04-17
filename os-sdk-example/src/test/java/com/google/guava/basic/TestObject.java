package com.google.guava.basic;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.junit.Test;

/**
 * Created by kingsonwu on 15/12/27.
 */
public class TestObject {

    @Test
    public void testEqual() {
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true

        //JDK7引入的Objects类提供了一样的方法Objects.equals。

    }

    public void testHashcode() {

        Objects.hashCode(1, "kxw");
    }

    public void testToString() {
        // Returns "ClassName{x=1}"
        Objects.toStringHelper(this).add("x", 1).toString();
        // Returns "MyObject{x=1}"
        Objects.toStringHelper("MyObject").add("x", 1).toString();
    }

    @Test
    public void testCompare() {

    }
}

class Person implements Comparable<Person> {
    private String lastName;
    private String firstName;
    private int zipCode;

   /* public int compareTo(Person other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp != 0) {
            return cmp;
        }
        cmp = firstName.compareTo(other.firstName);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(zipCode, other.zipCode);
    }*/

    //ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。
    public int compareTo(Person that) {
        return ComparisonChain.start()
                .compare(this.lastName, that.lastName)
                .compare(this.firstName, that.firstName)
                .compare(this.zipCode, that.zipCode, Ordering.natural().nullsLast())
                .result();
    }
    //这种Fluent接口风格的可读性更高，发生错误编码的几率更小，并且能避免做不必要的工作。

}
