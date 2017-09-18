package com.kxw;

import java.math.BigDecimal;

/**
 * Created by kingsonwu on 16/3/24.
 */
public class TestConstants {

    public static void main(String[] args) {
        int thousand = 1_000;
        int million = 1_000_000;

        System.out.println(thousand);
        System.out.println(million);

        //数值字面常量可以添加下划线是Java语言的新特性。这允许你使用_作为大数字的视觉分隔符。

        System.out.println(2*2*2*2*2*2*2*2);
        System.out.println(256*256);
        System.out.println(65536L*65536L);
        System.out.println(4294967296L*65536L*65536L);
        BigDecimal b1 = new BigDecimal(4294967296L);
        BigDecimal b2 = new BigDecimal(4294967296L);
        System.out.println(b1.multiply(b2));
    }
}
