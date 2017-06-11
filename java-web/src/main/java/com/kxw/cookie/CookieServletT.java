package com.kxw.cookie;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href='http://www.bysocket.com/?p=362'>@link</a>
 */
@WebServlet(urlPatterns = "/cookie")
public class CookieServletT extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取Cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }

        // 创建Cookie
        Cookie cookie = new Cookie("CookieName", "CookieValue");
        cookie.setMaxAge(10);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);

        // 响应
        PrintWriter pw = resp.getWriter();
        pw.print("<html><body><h1>Hello,Cookie!</h1></body></html>");
    }

}