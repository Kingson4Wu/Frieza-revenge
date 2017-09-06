package com.kxw.java8.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.primitives.Ints;

/**
 * Created by kingsonwu on 17/8/25.
 */
public class CollectionOperation {

    public static void main(String[] args) {

        //removeIf
        //通过传入Predicate删除符合条件的元素
        Collection<String> c = new HashSet<>();
        c.add("Content 1");
        c.add("Content 2");
        c.add("Content 3");
        c.removeIf(s -> s.contains("2"));
        System.out.println(c);
        // => [Content 3, Content 1]

        //基本操作
        List<Integer> list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        list.removeIf(a -> { return a % 3 == 0;});
        //OR 操作
        Predicate<Integer> predicate2 = a -> { return a % 3 == 0;};
        Predicate<Integer> predicate3 = a -> { return a % 5 == 0;};
        list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        list.removeIf(predicate2.or(predicate3));

        //AND 操作
        Predicate<Integer> predicate4 = a -> { return a % 3 == 0;};
        Predicate<Integer> predicate5 = a -> { return a % 5 == 0;};
        list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        list.removeIf(predicate4.and(predicate5));


        //forEachRemaining
        /*if (!iterator.hasNext()) { return;}
        StringBuilder builder = new StringBuilder();
        builder.append(iterator.next());
        iterator.forEachRemaining(element -> {
            builder.append(", ").append(element);
        });
        */

        //replaceAll
        //stringList.replaceAll(String::toUpperCase);

        //sort
        //List<String> stringList = Arrays.asList("a", "b", "c");
        // stringList.sort(String::compareTo);

        /**
         * Collections.sort() Collections sort list.sort
         public static <T extends Comparable<? super T>> void sort(List<T> list) { list.sort(null);
         ￼￼}
         */

        //compute(K,remappingFunction)
        //等价于

        /*V oldValue = map.get(key);
        V newValue = remappingFunction.apply(key, oldValue);
        if (oldValue != null) {
            if (newValue != null) {
                map.put(key, newValue);
            } else {
                map.remove(key);
            }

            ￼} else {
            if (newValue !=
                map.put(key,else
            return null;
        }*/

        //computeIfAbsent(K,mappingFunction) vs computeIfPresent(K,remappingFunction)
        //本地缓存使用场景

        //merge(K,V,remappingFunction)
        //reversed()
        //thenComparing(other)
        //reverseOrder
        //naturalOrder
        //nullsFirst(comparator) & nullsLast(comparator)

        //集合转数组
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        //原始方式
        Integer[] integerArray = integerList.toArray(new Integer[integerList.size()]);
        // guava
        int[] intArray = Ints.toArray(integerList);
        // java8-基本类型
        intArray = integerList.stream().mapToInt(Integer::intValue).toArray();
        // java8-对象类型
        String[] stringArray = stringList.stream().toArray(String[]::new);

    }
}
