<https://github.com/jdeferred/jdeferred>

Java 的 Deferred/Promise 库，与 JQuery 的 Deferred/Promise 相似
● Deferred 和 Promise 对象
● Promise 回调：.then(…), .done(…), .fail(…), .progress(…), .always(…)
● 同时处理多个 Promise - .when(p1, p2, p3, …).then(…)
● Callable 和 Runnable - wrappers.when(new Runnable() {…})
● 使用执行服务(ExecutorService)
● Java Generics 支持: Deferred<Integer, Exception, Doubledeferred;, deferred.resolve(10);, deferred.reject(new Exception());,deferred.notify(0.80);,
● 支持 Android
● 可以使用 Java 8 Lambda