package com.kxw.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kingson.wu on 2015/12/10.
 */
public class TestListStringConvert {

    public static void main(String[] args) {

        //ArrayList<String> places = new ArrayList<String>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        //import com.google.common.collect.ImmutableList;
        //List<String> places = ImmutableList.of("Buenos Aires", "Córdoba", "La Plata");

        /** 1.List转String[] */
        List<String> list1 = Arrays.asList("a", "b", "c");
        //List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        String[] str = list1.toArray(new String[list1.size()]);

        /** 2.String[]转List */
        String body = "a,b,c,d";
        String[] dataList = body.split(",");
        List<String> list2 = new ArrayList<>();
        list2 = Arrays.asList(dataList);

    }

   /* public Object[] toArray(){
        Object[] result = new Object[size];
        System.arraycopy(elementData, 0, result, 0, size);
        return result;
    }

    public Object[] toArray(Object a[]){
        if (a.length < size)
            a = (Object[]);java.lang.reflect.Array.newInstance(a.getClass().getComponentType().size);
        System.arraycopy(elementData, 0, a, 0, size);

        if (a.length > size)
            a[size] = null;

        return a;
    }*/
}
