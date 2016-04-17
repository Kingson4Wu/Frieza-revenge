package com.kxw.jdeferred;

import org.jdeferred.*;
import org.jdeferred.impl.DeferredObject;

/**
 * Created by kingsonwu on 16/1/30.
 */
public class TestPipe {

    public static void main(String[] args) {
        Deferred deferred = new DeferredObject();
        Promise p = deferred.promise();

        p.then(new DonePipe<Integer, Integer, Exception, Void>() {
            public Deferred<Integer, Exception, Void> pipeDone(Integer result) {
                if (result < 100) {
                    return new DeferredObject<Integer, Exception, Void>().resolve(result);
                } else {
                    return new DeferredObject<Integer, Exception, Void>().reject(new Exception("kxw"));
                }
            }
        }
        ).done(o -> System.out.println("done ...")
        ).fail(o -> System.out.println("failed ..."));

        //deferred.resolve(80);
        deferred.resolve(100) ;

    }
}
