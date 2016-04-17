package com.kxw.java8.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

/**
 * <a href='http://www.importnew.com/10815.html'>@link</a>
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureTest test = new CompletableFutureTest();
        System.out.println(test.ask().get());

        //---------
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //...long running...
            return "43";
        }, ForkJoinPool.commonPool());//不传executor默认ForkJoinPool.commonPool()

        System.out.println(future.get());

        //---------
        CompletableFuture<Double> f3 =
                future.thenApply(Integer::parseInt).thenApply(r -> r * r * Math.PI);

        System.out.println(f3.get());

        //----------

        future.thenAcceptAsync(dbl -> System.out.println(("Result: " + dbl)));
        System.out.println("continue ...");

        future.thenRun(() -> System.out.println("run ..."));

        //----------
        CompletableFuture<String> safe =
                future.exceptionally(ex -> "We have a problem: " + ex.getMessage());

        CompletableFuture<Integer> safe2 = future.handle((ok, ex) -> {
            if (ok != null) {
                return Integer.parseInt(ok);
            } else {
                System.out.println("Problem: " + ex);
                return -1;
            }
        });
        //-----------
        test.test();

    }

    private void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> docFuture = CompletableFuture.supplyAsync(() -> {
            //...long running...
            return "45";
        });

        CompletableFuture<CompletableFuture<Double>> f =
                docFuture.thenApply(this::calculateRelevance);

        CompletableFuture<Double> relevanceFuture =
                docFuture.thenCompose(this::calculateRelevance);

        System.out.println(f.get().get());
        System.out.println(relevanceFuture.get());
    }

    public CompletableFuture<String> ask() {
        final CompletableFuture<String> future = new CompletableFuture<>();
        //...
        future.complete("42");
        return future;
    }

    private CompletableFuture<Double> calculateRelevance(String doc){
        CompletableFuture<Double> future = new CompletableFuture<>();
        future.complete(Double.valueOf(doc));
        return future;
    }

}
