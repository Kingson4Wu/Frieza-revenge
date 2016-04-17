package com.google.guava.collections;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * {<a href='http://ifeve.com/google-guava-collectionhelpersexplained/'>@link</a>}
 */
public class CollectionsExtendUtilsTest {

    /**
     * Forwarding装饰器
     * 针对所有类型的集合接口，Guava都提供了Forwarding抽象类以简化装饰者模式的使用。
     * Forwarding抽象类定义了一个抽象方法：delegate()，你可以覆盖这个方法来返回被装饰对象。
     * 所有其他方法都会直接委托给delegate()。例如说：ForwardingList.get(int)实际上执行了delegate().get(int)。
     */

    public void testForwarding() {


    }

    /**
     * Iterators提供一个Iterators.peekingIterator(Iterator)方法，来把Iterator包装为PeekingIterator，
     * 这是Iterator的子类，它能让你事先窥视[peek()]到下一次调用next()返回的元素。
     * 注意：Iterators.peekingIterator返回的PeekingIterator不支持在peek()操作之后调用remove()方法。
     */
    @Test
    public void testPeekingIterator() {

        //复制一个List，并去除连续的重复元素。
        /**
         * 传统的实现方式需要记录上一个元素，并在特定情况下后退，但这很难处理且容易出错。
         * 相较而言，PeekingIterator在理解和使用上就比较直接了。
         */
        List<String> source = Lists.newArrayList("alpha", "beta", "gamma");

        List<String> result = Lists.newArrayList();
        PeekingIterator<String> iter = Iterators.peekingIterator(source.iterator());
        while (iter.hasNext()) {
            String current = iter.next();
            while (iter.hasNext() && iter.peek().equals(current)) {
                //跳过重复的元素
                iter.next();
            }
            result.add(current);
        }

    }

    //用一个例子来解释AbstractIterator最简单。比方说，我们要包装一个iterator以跳过空值。
    public void testAbstractIterator() {

    }

    public static Iterator<String> skipNulls(final Iterator<String> in) {
        return new AbstractIterator<String>() {
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
    }
    /**
     * 你实现了computeNext()方法，来计算下一个值。如果循环结束了也没有找到下一个值，请返回endOfData()表明已经到达迭代的末尾。
     注意：AbstractIterator继承了UnmodifiableIterator，所以禁止实现remove()方法。
     如果你需要支持remove()的迭代器，就不应该继承AbstractIterator。
     */

    public void testAbstractSequentialIterator(){


    }

    Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) { // 注意初始值1!
        protected Integer computeNext(Integer previous) {
            return (previous == 1 << 30) ? null : previous * 2;
        }
    };
    /**
     * 我们在这儿实现了computeNext(T)方法，它能接受前一个值作为参数。
     注意，你必须额外传入一个初始值，或者传入null让迭代立即结束。
     因为computeNext(T)假定null值意味着迭代的末尾——AbstractSequentialIterator不能用来实现可能返回null的迭代器。
     */



}
