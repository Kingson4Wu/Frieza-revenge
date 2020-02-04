package com.kxw.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class TestDateApi2 {

    public static void main(String[] args) {

        //https://majing.io/posts/10000015291215
        //Java 8的一个bug：https://bugs.openjdk.java.net/browse/JDK-8031085。
        String text = "20180225142051591";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            // 解析date+time
            .appendPattern("yyyyMMddHHmmss")
            // 解析毫秒数
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .toFormatter();
        formatter.parse(text);

        String text2 = "201802";

        DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
            // 解析date+time
            .appendPattern("yyyyMM")
            .toFormatter();
        formatter2.parse(text2);

        ZonedDateTime.now().format(formatter2);

        LocalDate localDate = LocalDate.parse("2019-05-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        //LocalDate date1 = LocalDate.parse(text2, formatter2);

        //LocalDate date = LocalDate.parse("201501", DateTimeFormatter.ofPattern("yyyyMM"));



    }
}
