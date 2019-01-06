package com.kxw.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class WiremockTest {

    @Rule
    public WireMockRule service1 = new WireMockRule(8081);

    @Rule
    public WireMockRule service2 = new WireMockRule(8082);

    @Test
    public void bothServicesDoStuff() {
        service1.stubFor(get(urlEqualTo("/blah")));
        service2.stubFor(post(urlEqualTo("/blap")));

        //只能mock本地服务？。。。

    }
}
