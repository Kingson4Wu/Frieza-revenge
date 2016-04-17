package com.kxw.rxjava;

import rx.Observable;

/**
 * Created by kingson.wu on 2015/12/1.
 */
public class Example {

    public static void main(String[] args) {
        String[] arr = {"url1", "url2", "url3"};
        Observable.from(arr).subscribe(url -> System.out.println(url));
        Observable.just(arr).subscribe(url -> System.out.println(url[0]));


        String[] arr1 = {"a", "b", "c"};
        Observable<String> o1 = Observable.from(arr1);

        Integer[] arr2 = {5, 6, 7, 8};
        Observable<Integer> o2 = Observable.from(arr2);

        Observable<String> o3 = Observable.just("one object");
        Observable.just("one object").subscribe(url -> System.out.println(url));
        //Observable.from("one object").subscribe(url -> System.out.println(url));

    }
}
/**
 You use the Observable just( ) and from( ) methods to convert objects, lists, or arrays of objects into Observables that emit those objects:

 Observable<String> o = Observable.from("a", "b", "c");

 def list = [5, 6, 7, 8]
 Observable<Integer> o = Observable.from(list);

 Observable<String> o = Observable.just("one object");
 These converted Observables will synchronously invoke the onNext( ) method of any subscriber that subscribes to them, for each item to be emitted by the Observable, and will then invoke the subscriber’s onCompleted( ) method.
 */