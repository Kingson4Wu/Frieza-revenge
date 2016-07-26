package com.kxw.test;

public class MemoryTest {
    public static void main(String[] args) {

        Runtime rt=Runtime.getRuntime( ); //获得系统的Runtime对象rt
        long start = rt.freeMemory();

     /*   Map<String,String> map = new HashMap<>(10000000);
        for(int i = 0 ;i <10000000;i++){
            map.put("dddd666666666666666666633fdvdvddvdfvd","fffdd");//67m
        }*/

        System.out.println(start - rt.freeMemory());

        long a =1l;
        int b = (int)a;
        System.out.println(b);
    }
}
