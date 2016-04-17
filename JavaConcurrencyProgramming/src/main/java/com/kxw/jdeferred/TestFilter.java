package com.kxw.jdeferred;

import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.DoneFilter;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

/**
 * Created by kingsonwu on 16/1/30.
 */
public class TestFilter {

    public static void main(String[] args) {
        Deferred deferred = new DeferredObject();
        Promise p = deferred.promise();
        Promise filtered = p.then(new DoneFilter<Integer, Integer>() {
            public Integer filterDone(Integer result) {
                return result * 10;
            }
        });

        filtered.done(new DoneCallback<Integer>() {
            public void onDone(Integer result) {
                // result would be original * 10
                System.out.println(result);
            }
        });

        deferred.resolve(3);
    }
}
