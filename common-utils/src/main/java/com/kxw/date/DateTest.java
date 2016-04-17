package com.kxw.date;

/**
 * Created by kxw on 2016/1/7.
 */
public class DateTest {
    public static void main(String[] args) {
        long time = System.currentTimeMillis() / 1000;//获取当前时间

        String temp = "20107829";
        System.out.println(isDateString(temp));
    }

    //判断20100303这种字符串是否是日期
    public static boolean isDateString(String data) {
        if (data.length() != 8) {
            return false;
        } else {
            int year = Integer.parseInt(data.substring(0, 4));
            int month = Integer.parseInt(data.substring(4, 6));
            int day = Integer.parseInt(data.substring(6, 8));
            System.out.println(year);
            System.out.println(month);
            System.out.println(day);
            boolean bool = false;//是否闰年

            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    bool = true;
                } else {
                    bool = false;
                }
            } else if (year % 4 == 0) {
                bool = true;
            }

            if (month < 1 || month > 12) {
                return false;
            } else {
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day < 1 || day > 31) {
                        return false;
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day < 1 || day > 30) {
                        return false;
                    }
                } else {
                    if (bool) {
                        if (day < 1 || day > 29) {
                            return false;
                        }
                    } else {
                        if (day < 1 || day > 28) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
