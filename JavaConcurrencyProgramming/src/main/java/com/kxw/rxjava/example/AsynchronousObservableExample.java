package com.kxw.rxjava.example;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by kingson.wu on 2015/12/1.
 */
public class AsynchronousObservableExample {
    public static void main(String[] args) {
        customObservableBlocking().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    public static Observable customObservableBlocking() {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getId());
                //新开一个线程就叫异步！！
                new Thread(() -> {
                    for (int i = 0; i < 75; i++) {
                        if (subscriber.isUnsubscribed()) {
                            break;
                        }
                        subscriber.onNext("value_" + i);

                    }
                    // after sending all values we complete the sequence
                    if (!subscriber.isUnsubscribed()) {
                        System.out.println(Thread.currentThread().getId());
                        subscriber.onCompleted();
                    }
                }).start();
            }
        });
    }
}
