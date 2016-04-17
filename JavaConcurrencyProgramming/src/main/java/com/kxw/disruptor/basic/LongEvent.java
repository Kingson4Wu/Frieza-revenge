package com.kxw.disruptor.basic;

/**
 * Created by kingson.wu on 2015/12/1.
 * {<a href='https://github.com/LMAX-Exchange/disruptor/wiki/Getting-Started'>@link</a>}
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }
}
