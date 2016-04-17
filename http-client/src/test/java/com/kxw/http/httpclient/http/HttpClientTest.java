package com.kxw.http.httpclient.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * {<a href='http://blog.csdn.net/techbirds_bao/article/details/9092071'>@link</a>}
 */
public class HttpClientTest {


    private Logger logger=Logger.getLogger(this.getClass().getName());

    /**
     * get,不带参数
     */
    @Test
    public void getHttp(){
        HttpClient http=new DefaultHttpClient();
        HttpGet get=new HttpGet("http://localhost:8080/urlrewrite/");
        try {
            HttpResponse resp=http.execute(get);
            HttpEntity entity=resp.getEntity();
            logger.info("content Length:"+entity.getContentLength());
            logger.info("content:"+ EntityUtils.toString(entity));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭连接，释放资源
            http.getConnectionManager().shutdown();
        }
    }

    /**
     * post带参
     */
    @Test
    public void postHttp(){
        HttpClient http=new DefaultHttpClient();
        HttpPost post=new HttpPost("http://localhost:8080/httpserver/test");
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "奥巴马"));
        params.add(new BasicNameValuePair("password", "123456"));
        try {
            UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"UTF-8");
            post.setEntity(formEntity);
            logger.info("uri:"+post.getURI());
            try {
                HttpResponse resp=http.execute(post);
                HttpEntity entity=resp.getEntity();
                logger.info("content length:"+entity.getContentLength());
                logger.info("content:"+EntityUtils.toString(entity,"UTF-8"));

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally{
            //关闭连接，释放资源
            http.getConnectionManager().shutdown();
        }

    }
}
