package com.kxw.properties.owner;

import org.aeonbits.owner.Config;

public interface ServerConfig extends Config {
    int port();
    String hostname();
    int maxThreads();
}