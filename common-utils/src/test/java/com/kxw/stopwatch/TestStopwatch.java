package com.kxw.stopwatch;

public class TestStopwatch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        long from = System.currentTimeMillis();

        try {
            Thread.sleep(1339);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long to = System.currentTimeMillis();
        long timeFromTo = to - from;
        String print = String.format("Total: %dms : [%s]=>[%s]",
                timeFromTo,
                new java.sql.Timestamp(from).toString(),
                new java.sql.Timestamp(to).toString());


        System.out.println(print);
    }

}
