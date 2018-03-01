package com.kxw.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxw on 2016/1/7.
 */
public class RemoveDuplicateElement {

    //不破坏顺序去重用arraylist
    public static String[] removeDuplicated(String[] arr) {

        List<String> list = new ArrayList<>();
        for (String str : arr) {
            if (list.contains(str)) {

                //logger.info(" {} is exist.",str);
            } else {
                list.add(str);
            }
        }
        return list.toArray(new String[list.size()]);

    }

}
