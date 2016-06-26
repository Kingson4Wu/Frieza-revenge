package com.kxw;

import java.lang.management.ManagementFactory;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class GetJVMArgs {

    public static void main(String[] args) {
        //使用java代码查看我设置的jvm参数
        System.out.println(ManagementFactory.getRuntimeMXBean().getInputArguments());
    }
}
