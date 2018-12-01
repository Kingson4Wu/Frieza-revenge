package com.kxw.http.httpclient.http;

import java.io.IOException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;

public class ApacheHttpClientTest {


    @Test
    public void test() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(20);
            /*connectionManager.setMaxTotal(1);
            connectionManager.setDefaultMaxPerRoute(1);*/
        int connectionRequestTimeout = 30;
        int readTimeout = 1;
        int connectTimeout = 80;

        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(100).setConnectTimeout
            (connectTimeout).setSocketTimeout(readTimeout).build();
        HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connectionManager)
            .setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();
           /* RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(80)
                .setConnectTimeout(50)
                .setConnectionRequestTimeout(30)
                .build();
            CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();*/

        HttpPost httpPost = new HttpPost("http://www.baidu.com");
        try {
            httpClient.execute(httpPost);
        } catch (IOException e) {
            if (e instanceof org.apache.http.conn.ConnectTimeoutException) {
                System.out.println("ConnectTimeoutException");
            }
            if (e instanceof java.net.SocketTimeoutException) {
                if ("Read timed out".equals(e.getMessage())) {
                    System.out.println("ReadTimeoutException");
                }
            }
        }

        //Caused by: java.net.SocketTimeoutException: connect timed out
        //org.apache.http.conn.ConnectTimeoutException
        //java.net.SocketTimeoutException: Read timed out

        /**
         * DefaultHttpClientConnectionOperator
         * try {
         *                 sock = sf.connectSocket(
         *                         connectTimeout, sock, host, remoteAddress, localAddress, context);
         *                 conn.bind(sock);
         *                 if (this.log.isDebugEnabled()) {
         *                     this.log.debug("Connection established " + conn);
         *                 }
         *                 return;
         *             } catch (final SocketTimeoutException ex) {
         *                 if (last) {
         *                     throw new ConnectTimeoutException(ex, host, addresses);
         *                 }
         *             } catch (final ConnectException ex) {
         *                 if (last) {
         *                     final String msg = ex.getMessage();
         *                     if ("Connection timed out".equals(msg)) {
         *                         throw new ConnectTimeoutException(ex, host, addresses);
         *                     } else {
         *                         throw new HttpHostConnectException(ex, host, addresses);
         *                     }
         *                 }
         *             }
         */
    }
}
