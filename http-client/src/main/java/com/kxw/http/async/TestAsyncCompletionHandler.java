package com.kxw.http.async;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * {<a href='https://github.com/AsyncHttpClient/async-http-client#usage'>@link</a>}
 */
public class TestAsyncCompletionHandler {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        Future<Integer> f = asyncHttpClient.prepareGet("http://www.ning.com/").execute(
                new AsyncCompletionHandler<Integer>() {
            @Override
            public Integer onCompleted(Response response) throws Exception {
                // Do something with the Response
                return response.getStatusCode();
            }
            @Override
            public void onThrowable(Throwable t){
                // Something wrong happened.
            }
        });

        int statusCode = f.get();

        System.out.println(statusCode);

    }

}
