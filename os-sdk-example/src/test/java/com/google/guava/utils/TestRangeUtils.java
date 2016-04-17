package com.google.guava.utils;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import com.google.common.primitives.Ints;

/**
 * {<a href='http://ifeve.com/google-guava-ranges/'>@link</a>}
 */
public class TestRangeUtils {

    public void test(){

        Range.closed("left", "right"); //字典序在"left"和"right"之间的字符串，闭区间
        Range.lessThan(4.0); //严格小于4.0的double值

        Range.downTo(4, BoundType.CLOSED);// (a..+∞)或[a..+∞)，取决于boundType
        Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN);// [1..4)，等同于Range.closedOpen(1, 4)

        //区间运算
        Range.closed(1, 3).contains(2);//return true
        Range.closed(1, 3).contains(4);//return false
        Range.lessThan(5).contains(5); //return false
        Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); //return true

        //查询运算
        Range.closedOpen(4, 4).isEmpty(); // returns true
        Range.openClosed(4, 4).isEmpty(); // returns true
        Range.closed(4, 4).isEmpty(); // returns false
        Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException
        Range.closed(3, 10).lowerEndpoint(); // returns 3
        Range.open(3, 10).lowerEndpoint(); // returns 3
        Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        Range.open(3, 10).upperBoundType(); // returns OPEN

        //关系运算
        //包含[enclose]
        //相连[isConnected]
        Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
        Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
        Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
        Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
        Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false

        //交集[intersection]
        Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
        Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
        Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
        Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE
        Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE

        //跨区间[span]
        Range.closed(3, 5).span(Range.open(5, 10)); // returns [3, 10)
        Range.closed(0, 9).span(Range.closed(3, 4)); // returns [0, 9]
        Range.closed(0, 5).span(Range.closed(3, 9)); // returns [0, 9]
        Range.open(3, 5).span(Range.open(5, 10)); // returns (3, 10)
        Range.closed(1, 5).span(Range.closed(6, 10)); // returns [1, 10]

        /**
         * 如果我需要一个Comparator呢？
         我们想要在Range的可用性与API复杂性之间找到特定的平衡，这部分导致了我们没有提供基于Comparator的接口：我们不需要操心区间是怎样基于不同Comparator互动的；所有API签名都是简单明确的；这样更好。

         另一方面，如果你需要任意Comparator，可以按下列其中一项来做：

         使用通用的Predicate接口，而不是Range类。（Range实现了Predicate接口，因此可以用Predicates.compose(range, function)获取Predicate实例）
         使用包装类以定义期望的排序。
         译者注：实际上Range规定元素类型必须是Comparable，这已经满足了大多数需求。如果需要自定义特殊的比较逻辑，可以用Predicates.compose(range, function)组合比较的function。
         */

    }


    /** RangeSet  RangeMap*/
    public void testRangeCollection(){

        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "aaa");
        System.out.println(rangeMap);
        rangeMap.put(Range.open(3, 6), "bbb");
        System.out.println(rangeMap);
        rangeMap.put(Range.openClosed(10, 20), "aaa");
        System.out.println(rangeMap);
        rangeMap.put(Range.closed(20, 20), "aaa");
        System.out.println(rangeMap);
        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap);
    }


}
