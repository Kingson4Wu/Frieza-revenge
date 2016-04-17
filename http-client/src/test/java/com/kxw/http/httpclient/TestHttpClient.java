package com.kxw.http.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * {<a href='http://rensanning.iteye.com/blog/1550436'>@link</a>}
 */
public class TestHttpClient {

    //1、基本请求
    @Test
    public void test() throws IOException {

        //创建一个客户端
        HttpClient client = new DefaultHttpClient();

        //创建一个get方法
        HttpGet get = new HttpGet("http://www.baidu.com");

        //执行请求
        HttpResponse res = client.execute(get);

        //获取协议版本・・・「HTTP/1.1」
        System.out.println(res.getProtocolVersion());
        //获取返回状态码・・・「200」
        System.out.println(res.getStatusLine().getStatusCode());
        //获取原因短语・・・「OK」
        System.out.println(res.getStatusLine().getReasonPhrase());
        //获取完整的StatusLine・・・「HTTP/1.1 200 OK」
        System.out.println(res.getStatusLine().toString());

        //获取返回头部信息
        Header[] headers = res.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header.getName() + ": " + header.getValue());
        }

        //获取返回内容
        if (res.getEntity() != null) {
            System.out.println(EntityUtils.toString(res.getEntity()));
        }

        //关闭流
        EntityUtils.consume(res.getEntity());

        //关闭连接
        client.getConnectionManager().shutdown();
    }

    //操作Cookie
    @Test
    public void cookie() throws IOException {
        //生成Cookie
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie stdCookie = new BasicClientCookie("name", "value");
        stdCookie.setVersion(1);
        stdCookie.setDomain(".baidu.com");
        stdCookie.setPath("/");
        stdCookie.setSecure(true);
        stdCookie.setAttribute(ClientCookie.VERSION_ATTR, "1");
        stdCookie.setAttribute(ClientCookie.DOMAIN_ATTR, ".baidu.com");
        cookieStore.addCookie(stdCookie);

        DefaultHttpClient client1 = new DefaultHttpClient();
        client1.setCookieStore(cookieStore);

        client1.execute(new HttpGet("http://www.baidu.com/"));

        //获取Cookie
        DefaultHttpClient client2 = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.baidu.com/");

        HttpResponse res = client2.execute(httpget);

        List<Cookie> cookies = client2.getCookieStore().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        }

        EntityUtils.consume(res.getEntity());
    }

    //3、设置代理
    @Test
    public void httpProxy() throws IOException {

        //通过连接参数设置代理
        DefaultHttpClient client1 = new DefaultHttpClient();

        HttpHost proxy1 = new HttpHost("192.168.2.60", 8080, "HTTP");
        client1.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy1);

        HttpGet get1 = new HttpGet("http://www.facebook.com");
        HttpResponse res1 = client1.execute(get1);
        if (res1.getEntity() != null) {
            System.out.println(EntityUtils.toString(res1.getEntity()));
        }
        EntityUtils.consume(res1.getEntity());

        //设置代理认证
        //client1.getCredentialsProvider().setCredentials(
        //        new AuthScope("localhost", 8080),
        //        new UsernamePasswordCredentials("username", "password"));
    }

    //4、POST数据
    @Test
    public void post() throws IOException {
        //========UrlEncodedFormEntity
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("param1", "value1"));
        formparams.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams, "UTF-8");

        HttpPost post1 = new HttpPost("http://localhost/post1.do");
        post1.setEntity(entity1);

        new DefaultHttpClient().execute(post1);

        //========FileEntity
        FileEntity entity2 = new FileEntity(new File("c:\\sample.txt"));
        entity2.setContentEncoding("UTF-8");

        HttpPost post2 = new HttpPost("http://localhost/post2.do");
        post2.setEntity(entity2);

        new DefaultHttpClient().execute(post2);

        //========MultipartEntity
        HttpPost post3 = new HttpPost("http://localhost/post3.do");
        File upfile = new File("C:\\test.jpg");
        MultipartEntity entity3 = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        entity3.addPart("file_name", new StringBody(upfile.getName()));
        entity3.addPart("file_contents", new FileBody(upfile));
        post3.setEntity(entity3);

        new DefaultHttpClient().execute(post3);
    }

    //5、URI构建
    @Test
    public void URI() throws URISyntaxException {
        //方法一
        URI uri1 = URIUtils.createURI("http", "www.baidu.com", -1, "/s",
                "wd=rensanning&rsv_bp=0&rsv_spt=3&inputT=1766", null);
        HttpGet httpget1 = new HttpGet(uri1);

        //http://www.baidu.com/s?wd=rensanning&rsv_bp=0&rsv_spt=3&inputT=1766
        System.out.println(httpget1.getURI());

        //方法二
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("wd", "rensanning"));
        qparams.add(new BasicNameValuePair("rsv_bp", "0"));
        qparams.add(new BasicNameValuePair("rsv_spt", "3"));
        qparams.add(new BasicNameValuePair("inputT", "1766"));

        URI uri2 = URIUtils.createURI("http", "www.baidu.com", -1, "/s",
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        HttpGet httpget2 = new HttpGet(uri2);

        //http://www.baidu.com/s?wd=rensanning&rsv_bp=0&rsv_spt=3&inputT=1766
        System.out.println(httpget2.getURI());
    }

    //6、使用Scheme
    @Test
    public void schema() throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //========将Scheme设置到client中
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();

        Scheme httpsScheme = new Scheme("https", 443, socketFactory);
        httpclient.getConnectionManager().getSchemeRegistry().register(httpsScheme);

        HttpGet httpget = new HttpGet("https://xxx.xxx.xxx");
        HttpResponse res = httpclient.execute(httpget);

        System.out.println(EntityUtils.toString(res.getEntity()));

        //========使用证书做SSL连接
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream fis = new FileInputStream("c:\\aa.keystore");
        try {
            keyStore.load(fis, "password".toCharArray());
        } finally {
            fis.close();
        }

        @SuppressWarnings("unused")
        SSLSocketFactory socketFactory2 = new SSLSocketFactory(keyStore);
        //......

    }

    //7、认证
    @Test
    public void credential() throws IOException {
        //========BASIC认证
        DefaultHttpClient httpclient1 = new DefaultHttpClient();

        UsernamePasswordCredentials creds1 = new UsernamePasswordCredentials("userid", "password");
        AuthScope auth1 = new AuthScope("localhost", 80);

        httpclient1.getCredentialsProvider().setCredentials(auth1, creds1);

        HttpGet httpget1 = new HttpGet("http://localhost/auth1");

        @SuppressWarnings("unused")
        HttpResponse res1 = httpclient1.execute(httpget1);

        //========BASIC认证(HttpContext)
        DefaultHttpClient httpclient2 = new DefaultHttpClient();

        UsernamePasswordCredentials creds2 = new UsernamePasswordCredentials("admin", "admin");
        AuthScope auth2 = new AuthScope("localhost", 80);

        httpclient2.getCredentialsProvider().setCredentials(auth2, creds2);

        HttpHost targetHost2 = new HttpHost("localhost", 80, "http");

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost2, basicAuth);

        BasicHttpContext localcontext2 = new BasicHttpContext();
        localcontext2.setAttribute(ClientContext.AUTH_CACHE, authCache);

        HttpGet httpget2 = new HttpGet("http://localhost/auth2");

        @SuppressWarnings("unused")
        HttpResponse res2 = httpclient2.execute(httpget2, localcontext2);

        //========DIGEST认证
        DefaultHttpClient httpclient3 = new DefaultHttpClient();

        UsernamePasswordCredentials creds3 = new UsernamePasswordCredentials("admin", "admin");
        AuthScope auth3 = new AuthScope("localhost", 80);

        httpclient3.getCredentialsProvider().setCredentials(auth3, creds3);

        HttpHost targetHost3 = new HttpHost("localhost", 80, "http");

        AuthCache authCache3 = new BasicAuthCache();
        DigestScheme digestAuth = new DigestScheme();
        digestAuth.overrideParamter("realm", "some realm");
        digestAuth.overrideParamter("nonce", "whatever");
        authCache3.put(targetHost3, digestAuth);

        BasicHttpContext localcontext3 = new BasicHttpContext();
        localcontext3.setAttribute(ClientContext.AUTH_CACHE, authCache3);

        HttpGet httpget3 = new HttpGet("http://localhost/auth3");

        @SuppressWarnings("unused")
        HttpResponse res3 = httpclient2.execute(httpget3, localcontext3);

        //========NTLM认证
        DefaultHttpClient httpclient4 = new DefaultHttpClient();

        NTCredentials creds4 = new NTCredentials("user", "pwd", "myworkstation", "microsoft.com");

        httpclient4.getCredentialsProvider().setCredentials(AuthScope.ANY, creds4);

        HttpHost targetHost4 = new HttpHost("hostname", 80, "http");

        HttpContext localcontext4 = new BasicHttpContext();

        HttpGet httpget4 = new HttpGet("http://localhost/auth4");

        @SuppressWarnings("unused")
        HttpResponse res4 = httpclient3.execute(targetHost4, httpget4, localcontext4);
    }

    //8、连接池
    @Test
    public void pool() throws IOException {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        //设置连接最大数
        cm.setMaxTotal(200);
        //设置每个Route的连接最大数
        cm.setDefaultMaxPerRoute(20);
        //设置指定域的连接最大数
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        HttpGet httpget = new HttpGet("http://localhost/pool");

        HttpClient client = new DefaultHttpClient(cm);

        @SuppressWarnings("unused")
        HttpResponse res = client.execute(httpget);

    }

}
