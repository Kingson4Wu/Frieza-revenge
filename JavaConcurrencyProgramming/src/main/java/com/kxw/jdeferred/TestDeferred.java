package com.kxw.jdeferred;

import org.jdeferred.*;
import org.jdeferred.impl.DeferredObject;

/**
 * Created by kingson.wu on 2015/12/3.
 * {<a href='https://github.com/jdeferred/jdeferred'>@link</a>}
 */
public class TestDeferred {

    public static void main(String[] args) {
        Deferred deferred = new DeferredObject();
        Promise promise = deferred.promise();
      /*  promise.done(new DoneCallback() {
            public void onDone(Object result) {
                System.out.println("doing");
            }
        }).fail(new FailCallback() {
            public void onFail(Object rejection) {
                //...
            }
        }).progress(new ProgressCallback() {
            public void onProgress(Object progress) {
                //...
            }
        }).always(new AlwaysCallback() {
            public void onAlways(Promise.State state, Object result, Object rejection) {
                //...
            }
        });*/

        promise.done(result -> {
            System.out.println("kxw done");
            System.out.println(result);
        }).fail(rejection -> {
            System.out.println("failed");
            System.out.println(rejection);
        }).progress(progress -> {
            System.out.println("progress");
            System.out.println(progress);
        }).always((state, result, rejection) -> {
            System.out.println("---");
        });

        deferred.resolve("done 66");//done
        //deferred.reject("oops");//fail
        //deferred.notify("100%");//progress



        Promise promise2 = deferred.promise();
        promise2.done(result -> {
            System.out.println("kxw2 done");
            System.out.println(result);
        }).fail(rejection -> {
            System.out.println("failed2");
            System.out.println(rejection);
        }).progress(progress -> {
            System.out.println("progress2");
            System.out.println(progress);
        }).always((state, result, rejection) -> {
            System.out.println("---2");
        });

        //deferred.resolve("done 662");//done
    }
}
