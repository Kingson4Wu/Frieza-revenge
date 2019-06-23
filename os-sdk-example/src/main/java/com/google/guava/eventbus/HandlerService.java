package com.google.guava.eventbus;

import com.google.common.eventbus.Subscribe;

public interface HandlerService {

    @Subscribe
    void handler(EmptyEvent emptyEvent);

}
