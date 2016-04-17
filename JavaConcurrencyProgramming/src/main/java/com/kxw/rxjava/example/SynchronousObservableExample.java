package com.kxw.rxjava.example;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by kingson.wu on 2015/12/1.
 */
public class SynchronousObservableExample {

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
                for (int i = 1; i <= 50; i++) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext("value_" + i);
                    }
                }
                if (!subscriber.isUnsubscribed()) {
                    System.out.println(Thread.currentThread().getId());
                    subscriber.onCompleted();
                }
            }
        });
    }
}


