package com.google.guava.utils;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.io.Charsets;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * {<a href='http://ifeve.com/google-guava-strings/'>@link</a>}
 */
public class TestStringUtils {

    @Test
    public void testJoiner(){
        Joiner joiner = Joiner.on("; ").skipNulls();
        String result =  joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(result);
        //上述代码返回”Harry; Ron; Hermione”。另外，useForNull(String)方法可以给定某个字符串来替换null，
        // 而不像skipNulls()方法是直接忽略null。 Joiner也可以用来连接对象类型，在这种情况下，
        // 它会把对象的toString()值连接起来。

        Joiner.on(",").join(Arrays.asList(1, 5, 7)); // returns "1,5,7"
    }


    @Test
    public void testSplitter(){

        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
        //上述代码返回Iterable<String>，其中包含”foo”、”bar”和”qux”。
        // Splitter可以被设置为按照任何模式、字符、字符串或字符匹配器拆分。

        /**拆分器工厂

        方法	描述	范例
        Splitter.on(char)	按单个字符拆分	Splitter.on(‘;’)
        Splitter.on(CharMatcher)	按字符匹配器拆分	Splitter.on(CharMatcher.BREAKING_WHITESPACE)
        Splitter.on(String)	按字符串拆分	Splitter.on(“,   “)
        Splitter.on(Pattern) Splitter.onPattern(String)	按正则表达式拆分	Splitter.onPattern(“\r?\n”)
        Splitter.fixedLength(int)	按固定长度拆分；最后一段可能比给定长度短，但不会为空。	Splitter.fixedLength(3)
        拆分器修饰符

        方法	描述
        omitEmptyStrings()	从结果中自动忽略空字符串
        trimResults()	移除结果字符串的前导空白和尾部空白
        trimResults(CharMatcher)	给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
        limit(int)	限制拆分出的字符串数量
         */
        /**
         * 如果你想要拆分器返回List，只要使用Lists.newArrayList(splitter.split(string))或类似方法。
         * 警告：splitter实例总是不可变的。用来定义splitter目标语义的配置方法总会返回一个新的splitter实例。
         * 这使得splitter实例都是线程安全的，你可以将其定义为static final常量。
         */

    }

    public void testCharMatcher(){

        String string = "";

        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        //去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
        // 只保留数字和小写字母

        /**
         * 注：CharMatcher只处理char类型代表的字符；它不能理解0x10000到0x10FFFF的Unicode 增补字符。
         * 这些逻辑字符以代理对[surrogate pairs]的形式编码进字符串，而CharMatcher只能将这种逻辑字符看待成两个独立的字符
         */
    }

    public void testCharsets(){
        try {
            String string = "kxw";
            byte[] bytes = string.getBytes("UTF-8");

            bytes = string.getBytes(Charsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            // how can this possibly happen?
            throw new AssertionError(e);
        }

    }

    @Test
    public void testCaseFormat(){
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"); // returns "constantName"
        //我们CaseFormat在某些时候尤其有用，比如编写代码生成器的时候。

    }
}
