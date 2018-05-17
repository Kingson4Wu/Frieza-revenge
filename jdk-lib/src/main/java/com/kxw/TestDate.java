package com.kxw;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

    public static void main(String[] args) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日 HH:mm:ss");
        Date date = new Date(Long.parseLong("1525679690") * 1000);

        System.out.println(simpleDateFormat.format(date));
    }
}
