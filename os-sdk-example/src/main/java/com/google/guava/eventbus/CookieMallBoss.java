package com.google.guava.eventbus;

import com.google.common.eventbus.EventBus;

public class CookieMallBoss implements HandlerService {

    public CookieMallBoss(EventBus eventBus) {
        eventBus.register(this);
    }

    public void handler(EmptyEvent emptyEvent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getClass().getName() + ":" + "receiving empty event");
    }
}