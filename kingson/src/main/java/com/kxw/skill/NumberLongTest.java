package com.kxw.skill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingsonwu on 18/3/28.
 */
public class NumberLongTest {

    public static void main(String[] args) {

        List<Number> numberList = new ArrayList<>();

        long value = 12;
        numberList.add(value);

        if(numberList.contains(12)){
            //o.getClass() 默认是Integer
            System.out.println("contains 1-" + value);
        }
        if(numberList.contains(12L)){
            System.out.println("contains 2-" + value);
        }
    }
}
