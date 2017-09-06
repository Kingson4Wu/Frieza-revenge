package com.kxw.java8;

import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * Created by kingsonwu on 17/8/25.
 */
public class TestString {
    public static void main(String[] args) {

        //join()。
        String.join(":", "alibaba", "icbu", "youguang");
        String join = String.join(":", Lists.newArrayList("alibaba", "icbu", "youguang"));
        System.out.println(join);
        // => alibaba:icbu:youguang

        //统计去重
        long collect = "alibaba:icbu:youguang".chars().distinct().count();
        System.out.println(collect);
        // => 11

        //StringJoiner
        StringJoiner sj = new StringJoiner(":");
        sj.add("alibaba");
        sj.add("icbu");
        sj.add("youguang");
        String result = sj.toString();
        //alibaba:icbu:youguang
        sj = new StringJoiner(":","prefix-", "-suffix");
        sj.add("alibaba");
        sj.add("icbu");
        sj.add("youguang");
        System.out.println(sj.toString());
        //prefix-alibaba:icbu:youguang-suffix

        //Pattern.splitAsStream() 过滤
        String bar = Pattern.compile(":")
            .splitAsStream("alibaba:icbu:youguang")
            .filter(s -> !s.contains("youguang"))
            .collect(Collectors.joining(":"));
        System.out.println(bar);
        // => alibaba:icbu

        //Pattern.asPredicate() 统计匹配的个数
        Pattern pattern = Pattern.compile(".*@gmail\\.com");
        Stream.of("tony.liw@gmail.com", "tony.liw@alibaba-inc.com")
            .filter(pattern.asPredicate()).count();
        // => 1



    }
}
