package com.kxw;

/**
 * Created by kingsonwu on 16/1/14.
 */
public class ObjectReferenceTest {

    public static void main(String[] args) {

        King k = new King();
        k.setId(2);
        k.setName("hehe");

        test(k);
        System.out.println(k.getName());

        test2(k);
        System.out.println(k.getName());
    }


    private static void test(King ele) {

        King k = new King();
        k.setId(2);
        k.setName("kxw");
        ele = k;
        //System.out.println(ele.getName() + "---");

    }

    private static void test2(King ele) {
        ele.setName("ddd");
    }


}

class King {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}