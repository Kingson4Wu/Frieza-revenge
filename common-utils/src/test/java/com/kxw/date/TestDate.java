package com.kxw.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kingson.wu on 2015/12/31.
 */
public class TestDate {

    public static void main(String[] args) throws ParseException {


        DateFormat df = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.ENGLISH);

        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date= cal.getTime();
        System.out.println(df.format(date));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        date= cal.getTime();
        System.out.println(df.format(date));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        date= cal.getTime();
        System.out.println(df.format(date));
    }
}
