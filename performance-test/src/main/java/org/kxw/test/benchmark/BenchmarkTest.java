package org.kxw.test.benchmark;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by kingsonwu on 17/2/5.
 * JMH是一个用于java或者其他JVM语言的，提供构建，运行和分析（按照多种基准：纳秒，微妙、毫秒、宏）的工具。
 * <a href = 'http://openjdk.java.net/projects/code-tools/jmh/'>@link</a>
 * <a href = 'http://www.hollischuang.com/archives/1072'>@link</a>
 * ➜  Utils4Java git:(master) ✗ cd performance-test
 * ➜  performance-test git:(master) ✗ mvn clean install java -jar
 * ➜  performance-test git:(master) ✗  java -jar target/benchmarks.jar
 */
public class BenchmarkTest {

    @Benchmark
    public void test(){

    }

}
