package com.kxw;

import java.lang.management.ManagementFactory;
import java.util.Map;

/**
 * Created by Kingson.wu on 2015/7/17.
 */
public class PID
{
    public static void main(String[] args)
    {
        // 在windows上，获取到得name格式为 1234@userName
        // 1234为PID，@为分隔符，userName为当前用户
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        Map<String ,String> path = ManagementFactory.getRuntimeMXBean().getSystemProperties();
        int indexOf = pid.indexOf('@');
        if (indexOf > 0)
        {
            pid = pid.substring(0, indexOf);
        }

        System.out.println("当前JVM Process ID: " + pid);
    }
}