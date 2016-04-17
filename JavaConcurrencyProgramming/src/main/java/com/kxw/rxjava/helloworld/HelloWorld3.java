package com.kxw.rxjava.helloworld;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by kingson.wu on 2015/12/2.
 * {<a href='http://blog.csdn.net/lzyzsd/article/details/44891933'>@link</a>}
 * {<a href='http://blog.danlew.net/2014/09/30/grokking-rxjava-part-3/'>@link</a>}
 */
public class HelloWorld3 {

    public static void main(String[] args) {
        //错误处理
        //到目前为止，我们都没怎么介绍onComplete()和onError()函数。这两个函数用来通知订阅者，
        // 被观察的对象将停止发送数据以及为什么停止（成功的完成或者出错了）。
        Observable.just("Hello, world!")
                .map(s -> potentialException(s))
                .map(s -> anotherPotentialException(s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Ouch!");
                    }
                });

        //----------------
        //订阅（Subscriptions）
        // 当调用Observable.subscribe()，会返回一个Subscription对象。这个对象代表了被观察者和订阅者之间的联系。

        Subscription subscription = Observable.just("Hello, World!")
                .subscribe(s -> System.out.println(s));
        //你可以在后面使用这个Subscription对象来操作被观察者和订阅者之间的联系.
        subscription.unsubscribe();
        System.out.println("Unsubscribed=" + subscription.isUnsubscribed());

        // Outputs "Unsubscribed=true"
        //RxJava的另外一个好处就是它处理unsubscribing的时候，会停止整个调用链。如果你使用了一串很复杂的操作符，
        // 调用unsubscribe将会在他当前执行的地方终止。不需要做任何额外的工作！

    }

    private static String potentialException(String s) {

        System.out.println(s);
        if (s.equals("a")) {
            //throw new Exception("aaa");
        }

        return s;

    }

    private static String anotherPotentialException(String s) {

        System.out.println(s);
        if (s.equals("b")) {
            //throw new Exception("bbb");
        }
        return s;
    }
    /**
     * 代码中的potentialException() 和 anotherPotentialException()有可能会抛出异常。每一个Observerable对象在终结的时候都会调用onCompleted()或者onError()方法，所以Demo中会打印”Completed!”或者”Ouch!”。

     这种模式有以下几个优点：

     1.只要有异常发生onError()一定会被调用

     这极大的简化了错误处理。只需要在一个地方处理错误即可以。

     2.操作符不需要处理异常

     将异常处理交给订阅者来做，Observerable的操作符调用链中一旦有一个抛出了异常，就会直接执行onError()方法。

     3.你能够知道什么时候订阅者已经接收了全部的数据。

     知道什么时候任务结束能够帮助简化代码的流程。（虽然有可能Observable对象永远不会结束）

     我觉得这种错误处理方式比传统的错误处理更简单。传统的错误处理中，通常是在每个回调中处理错误。这不仅导致了重复的代码，并且意味着每个回调都必须知道如何处理错误，你的回调代码将和调用者紧耦合在一起。

     使用RxJava，Observable对象根本不需要知道如何处理错误！操作符也不需要处理错误状态-一旦发生错误，就会跳过当前和后续的操作符。所有的错误处理都交给订阅者来做。
     */


}

