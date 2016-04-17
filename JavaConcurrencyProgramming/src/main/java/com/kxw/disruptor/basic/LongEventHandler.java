package com.kxw.disruptor.basic;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event.get() + " thread id: " + Thread.currentThread().getId());
    }
}