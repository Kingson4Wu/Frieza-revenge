package com.kxw.java8.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * <a href='http://www.importnew.com/17313.html'>@link</a>
 */
public class TestStreams {

    public static void main(String[] args) {
/*
        Name     | City       | Number of Sales |
        +----------+------------+-----------------+
                | Alice    | London     | 200             |
        | Bob      | London     | 150             |
        | Charles  | New York   | 160             |
        | Dorothy  | Hong Kong  | 190*/

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Bob", "London", 150));
        employees.add(new Employee("Alice", "London", 200));
        employees.add(new Employee("Charles", "New York", 160));
        employees.add(new Employee("Dorothy", "Hong Kong", 190));

        //分组
        // 首先，我们利用（lambda表达式出现之前的）命令式风格Java 程序对流中的雇员按城市进行分组

        Map<String, List<Employee>> result = new HashMap<>();
        for (Employee e : employees) {
            String city = e.getCity();
            List<Employee> empsInCity = result.get(city);
            if (empsInCity == null) {
                empsInCity = new ArrayList<>();
                result.put(city, empsInCity);
            }
            empsInCity.add(e);
        }


        //而在 Java 8 中，你可以使用 groupingBy 收集器，一条语句就能完成相同的功能
        Map<String, List<Employee>> employeesByCity =
                employees.stream().collect(groupingBy(Employee::getCity));

        System.out.println(result);
        System.out.println("---------");
        System.out.println(employeesByCity);
        System.out.println("---------");

        //还可以计算每个城市中雇员的数量，只需传递一个计数收集器给 groupingBy 收集器。
        // 第二个收集器的作用是在流分类的同一个组中对每个元素进行递归操作。

        Map<String, Long> numEmployeesByCity =
                employees.stream().collect(groupingBy(Employee::getCity, counting()));

        System.out.println(numEmployeesByCity);
        System.out.println("---------");
        /**
         * 顺便提一下，该功能与下面的 SQL 语句是等同的：
         select city, count(*) from Employee group by city
         */

        //另一个例子是计算每个城市的平均年龄，这可以联合使用 averagingInt 和 groupingBy 收集器
        Map<String, Double> avgSalesByCity =
                employees.stream().collect(groupingBy(Employee::getCity,
                        averagingInt(Employee::getScales)));
        System.out.println(avgSalesByCity);
        System.out.println("---------");

        //分区
        //分区是一种特殊的分组，结果 map 至少包含两个不同的分组——一个true，一个false。
        // 例如，如果想找出最优秀的员工，你可以将所有雇员分为两组，一组销售量大于 N，另一组小于 N，
        // 使用 partitioningBy 收集器
        Map<Boolean, List<Employee>> partitioned =
                employees.stream().collect(partitioningBy(e -> e.getScales() > 150));

        System.out.println(partitioned);
        System.out.println("--------");

        //也可以将 groupingBy 收集器传递给 partitioningBy 收集器来将联合使用分区和分组。
        // 例如，你可以统计每个分区中的每个城市的雇员人数
        Map<Boolean, Map<String, Long>> result2 =
                employees.stream().collect(partitioningBy(e -> e.getScales()> 150,
                        groupingBy(Employee::getCity, counting())));

        System.out.println(result2);
        System.out.println("---------");


    }
}

