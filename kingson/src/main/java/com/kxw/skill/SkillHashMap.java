package com.kxw.skill;


import java.util.HashMap;
import java.util.Map;

public class SkillHashMap {


    private static Map<Integer, String> identifier = new HashMap<Integer, String>() {{
        put(1, "44");
        put(2, "psse");
        System.out.println(identifier);
    }};

    /**
     * 利用两个花括号进行初始化是有另一套初始化过程的。这里，我们用了一个匿名类来初始化一个List，当要打印NAMES时，实际上打印出来的是null，这是因为初始化程序尚未完成，此时的list是空的。
     *
     * 关于使用两个花括号进行容器的初始化，可参考这里（http://www.c2.com/cgi/wiki?DoubleBraceInitialization）。
     *
     */

    public static void main(String[] args) {
       /* Kingson kingson = new Kingson() {{
            setCompany("vips");
            setName("torres");
        }};*/

        System.out.println(279622400/1024/1024);
    }
}
