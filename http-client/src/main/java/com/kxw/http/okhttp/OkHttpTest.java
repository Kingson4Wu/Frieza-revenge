package com.kxw.http.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kingsonwu on 17/3/27.
 *
 * @author kingsonwu
 * @date 2017/03/27
 */
public class OkHttpTest {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url("https://baidu.com")
            .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }


}
