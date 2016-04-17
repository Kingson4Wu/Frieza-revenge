package com.kxw.http.async;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by kingsonwu on 15/12/13.
 * {<a href='https://github.com/AsyncHttpClient/async-http-client'>@link</a>}
 */
public class TestAsyncHttpClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        Future<Response> f = asyncHttpClient.prepareGet("http://www.ning.com/").execute();
        Response r = f.get();

        System.out.println(r.getResponseBody());


        //------

     /*   AsyncHttpClientConfig cf = new AsyncHttpClientConfig.Builder();
        S.setProxyServer(new ProxyServer("127.0.0.1", 38080)).build();
        AsyncHttpClient c = new AsyncHttpClient(cf);*/
    }

}
