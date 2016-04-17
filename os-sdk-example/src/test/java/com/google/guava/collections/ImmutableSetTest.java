package com.google.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;

/**
 * {<a href ='http://ifeve.com/google-guava-immutablecollections/'>@link</a>}
 */

/**
 * 为什么要使用不可变集合
 不可变对象有很多优点，包括：

 当对象被不可信的库调用时，不可变形式是安全的；
 不可变对象被多个线程调用时，不存在竞态条件问题
 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 不可变对象因为有固定不变，可以作为常量来安全使用。
 创建对象的不可变拷贝是一项很好的防御性编程技巧。Guava为所有JDK标准集合类型和Guava新集合类型都提供了简单易用的不可变版本。
 JDK也提供了Collections.unmodifiableXXX方法把集合包装为不可变形式，但我们认为不够好：

 笨重而且累赘：不能舒适地用在所有想做防御性拷贝的场景；
 不安全：要保证没人通过原集合的引用进行修改，返回的集合才是事实上不可变的；
 低效：包装过的集合仍然保有可变集合的开销，比如并发修改的检查、散列表的额外空间，等等。
 如果你没有修改某个集合的需求，或者希望某个集合保持不变时，把它防御性地拷贝到不可变集合是个很好的实践。

 重要提示：所有Guava不可变集合的实现都不接受null值。我们对Google内部的代码库做过详细研究，
 发现只有5%的情况需要在集合中允许null元素，剩下的95%场景都是遇到null值就快速失败。如果你需要在不可变集合中使用null，
 请使用JDK中的Collections.unmodifiableXXX方法。更多细节建议请参考“使用和避免null”。
 */
public class ImmutableSetTest {

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

   /* class Foo {
        Set<Bar> bars;
        Foo(Set<Bar> bars) {
            this.bars = ImmutableSet.copyOf(bars); // defensive copy!
        }
    }*/

    public static void main(String[] args) {
        ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "baz");
        thingamajig(foobar);
    }

    static void thingamajig(Collection<String> collection) {
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(collection);
    }


/**
 * 怎么使用不可变集合
 不可变集合可以用如下多种方式创建：

 copyOf方法，如ImmutableSet.copyOf(set);
 of方法，如ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);
 Builder工具，如
 public static final ImmutableSet<Color> GOOGLE_COLORS =
 ImmutableSet.<Color>builder()
 .addAll(WEBSAFE_COLORS)
 .add(new Color(0, 191, 255))
 .build();
 此外，对有序不可变集合来说，排序是在构造集合的时候完成的，如：

 ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
 会在构造时就把元素排序为a, b, c, d。
 */

}
/**
 * 细节：关联可变集合和不可变集合
 可变集合接口	属于JDK还是Guava	不可变版本
 Collection	JDK	ImmutableCollection
 List	JDK	ImmutableList
 Set	JDK	ImmutableSet
 SortedSet/NavigableSet	JDK	ImmutableSortedSet
 Map	JDK	ImmutableMap
 SortedMap	JDK	ImmutableSortedMap
 Multiset	Guava	ImmutableMultiset
 SortedMultiset	Guava	ImmutableSortedMultiset
 Multimap	Guava	ImmutableMultimap
 ListMultimap	Guava	ImmutableListMultimap
 SetMultimap	Guava	ImmutableSetMultimap
 BiMap	Guava	ImmutableBiMap
 ClassToInstanceMap	Guava	ImmutableClassToInstanceMap
 Table	Guava	ImmutableTable
 */