package com.kxw.response;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kxw on 2016/1/7.
 */
public class ResponePage2 {

    //response输出网页
    @RequestMapping(value="/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String html = "<html><head></head><body><form method='post' action=''><input type='submit' value='submit' /></form></body></html>";
        response.setContentType("text/html");
        response.getWriter().println(html);
    }
}
