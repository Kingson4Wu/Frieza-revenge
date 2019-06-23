package com.google.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * https://www.cnblogs.com/jun-ma/p/4852133.html
 */
public class CookieContainerTest {

    @Test
    public void should_recv_event_message() throws Exception {
        EventBus eventBus = new EventBus();
        CookieContainer cookieContainer=new CookieContainer(eventBus);
        HandlerService cookieSeller = new CookieSeller(eventBus);
        HandlerService cookieMallBoss = new CookieMallBoss(eventBus);

        //设置cookie的数量为3
        cookieContainer.setNumberOfCookie(3);
        //用户取三次之后cookie数量为空
        cookieContainer.getACookie();
        cookieContainer.getACookie();
        cookieContainer.getACookie();
        System.out.println("=======再次取cookie, 触发Empty事件发布============");
        cookieContainer.getACookie();
    }
}
