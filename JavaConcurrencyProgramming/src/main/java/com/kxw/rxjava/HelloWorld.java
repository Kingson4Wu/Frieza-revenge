package com.kxw.rxjava;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by kingson.wu on 2015/12/1.
 * {<a href = 'https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava'>@link</a>}
 */
public class HelloWorld {

    public static void main(String[] args) {

        hello("kxw");
        hello("Torres","Messi");

    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }
        });
    }
}

