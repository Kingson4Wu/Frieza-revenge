package com.kxw.http.httpclient;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

/**
 * Created by kingsonwu on 18/7/22.
 */
public class TestNginxCheck {

    @Test
    public void testNginxCheckout() {
        int failCount = 0;
        int[] count = new int[4];
        Map<String, Integer> map = new HashMap<>();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url("http://nginx.inner.proxy.com/nginx/hello")
                .build();

            long stopTime = Instant.now().plusSeconds(120).toEpochMilli();
            while (System.currentTimeMillis() < stopTime) {
                Response response = client.newCall(request).execute();

                System.out.println("code: " + response.code() + ", remote: " + response.header("X-Application-Context")
                    + ", resp:" + response.body().string());
                if (response.code() != 200) {
                    failCount++;
                }
                if (Objects.nonNull(response.header("X-Application-Context"))) {
                    count[Integer.valueOf(response.header("X-Application-Context").split(":")[1]) - 8081]++;
                    if (map.containsKey(response.header("X-Application-Context"))) {
                        map.put(response.header("X-Application-Context"), map.get(response.header(
                            "X-Application-Context"))
                            + 1);
                    } else {
                        map.put(response.header("X-Application-Context"), 1);
                    }
                }

            }

            System.out.println("count: " + failCount);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (int i = 0; i < count.length; i++) {
                System.out.println(count[i]);
            }

            for (Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }

    }
}
