package com.kxw.servlet3;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import java.io.*;

public class AsyncListenerDemo implements AsyncListener {
    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
    }

    @Override
    public void onComplete(AsyncEvent event) {
        System.out.println("-----------------------Complete");
    }

    @Override
    public void onTimeout(AsyncEvent event) {
    }

    @Override
    public void onError(AsyncEvent event) {
    }
}

/**
 * 在Servlet异步处理处添加：
 actx.addListener(new MyListener())；就可以添加监听器，每当异步处理完成时就会触发onComplete()事件，输出Complete；
 */