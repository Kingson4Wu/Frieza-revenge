package com.kxw.reactor;

import reactor.Environment;
import reactor.rx.broadcast.Broadcaster;

public class ReactorHelloWorld {
    public static void main(String... args) throws InterruptedException {
        Environment.initialize();

        Broadcaster<String> sink = Broadcaster.create(Environment.get());

        sink.dispatchOn(Environment.cachedDispatcher())
                .map(String::toUpperCase)
                .consume(s -> System.out.printf("s=%s%n", s));

        sink.onNext("Hello World!");

        Thread.sleep(500);
    }
}
/**
 * 	If using Reactor’s static Environment instance, it must be initialized before calling Environment.get(). You could also do this in a static {} block.
 A Broadcaster is a special kind of Stream that allows publishing of values.
 Dispatch downstream tasks onto a load-balanced Dispatcher.
 Transform input to upper-case and implicitly broadcast downstream.
 Consume the transformed input and print to STDOUT.
 Publish a value into the Stream.
 Block the main thread until work on other threads is complete, otherwise we won’t see any output.
 */