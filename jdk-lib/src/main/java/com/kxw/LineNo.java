package com.kxw;

/**
 * <a href='http://coolshell.cn/articles/611.html'>@link</a>
 */
public class LineNo {
    public static int getLineNumber() {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int length = stackTraceElements.length;

        return Thread.currentThread().getStackTrace()[length - 1].getLineNumber();
    }

    public static int getLineNumber2() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return getLineNumber();
    }

    public static String getFileName() {
        return Thread.currentThread().getStackTrace()[2].getFileName();
    }

    public static void main(String args[]) {
        System.out.println("[" + getFileName() + "：" + getLineNumber2() + "]" + "Hello World!");
    }
}
