package com.kxw.properties.owner;

import org.aeonbits.owner.ConfigFactory;

/**
 * Created by kingsonwu on 15/12/13.
 * {<a href='http://hao.jobbole.com/owner/'>@link</a>}
 * {<a href='http://owner.aeonbits.org/docs/usage/'>@link</a>}
 */
public class TestOwner {


    public static void main(String[] args) {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        System.out.println("Server " + cfg.hostname() + ":" + cfg.port() +
                " will run " + cfg.maxThreads());
    }
}
