package com.kxw.http.httpclient.https;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <a href='http://blog.csdn.net/techbirds_bao/article/details/9092071'>@link</a>
 *
 * 开启tomcat的https
 */
public class TestHttps {


    private Logger logger=Logger.getLogger(this.getClass().getName());

    @Test
    public void getHttps(){
        HttpClient hc=new DefaultHttpClient();
        try {
            KeyStore ks=KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                FileInputStream fis=new FileInputStream(new File("d:\\tomcat.keystore"));
                try {
                    ks.load(fis,"123456".toCharArray());
                    try {
                        SSLSocketFactory ssf=new SSLSocketFactory(ks);
                        Scheme sch=new Scheme("https", 8443, ssf);
                        hc.getConnectionManager().getSchemeRegistry().register(sch);
                        HttpPost post=new HttpPost("https://localhost:8443/httpserver/test");
                        List<NameValuePair> params=new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("username", "奥巴马"));
                        params.add(new BasicNameValuePair("password", "123456"));
                        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"UTF-8");
                        post.setEntity(formEntity);
                        HttpResponse resp=hc.execute(post);
                        HttpEntity entity=resp.getEntity();
                        logger.info("content length:"+entity.getContentLength());
                        logger.info("content:"+ EntityUtils.toString(entity, "UTF-8"));

//                      HttpGet get=new HttpGet("https://localhost:8443/httpserver/test");
//                      HttpResponse resp=hc.execute(get);
//                      HttpEntity entity=resp.getEntity();
//                      logger.info("content length:"+entity.getContentLength());
//                      logger.info("content:"+EntityUtils.toString(entity,"UTF-8"));

                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (UnrecoverableKeyException e) {
                        e.printStackTrace();
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }
}
