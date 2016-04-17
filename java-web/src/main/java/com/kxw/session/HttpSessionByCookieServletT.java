package com.kxw.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * Copyright [2015] [Jeff Lee]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Jeff Lee
 * @since 2015-7-12 10:58:28
 * HttpSession的默认Cookie实现案例
 * <a href='http://m.oschina.net/blog/477535'>@link</a>
 */
@WebServlet(urlPatterns = "/sessionByCookie")
public class HttpSessionByCookieServletT extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {

        // 获取session
        // 如果是第一次请求的话，会创建一个HttpSeesion，等同于 req.getSession(true);
        // 如果已存在session，则会获取session。
        HttpSession session = req.getSession();

        if (session.isNew()) {
            // 设置session属性值
            session.setAttribute("name", "Jeff");
        }
        // 获取SessionId
        String sessionId = session.getId();

        PrintWriter out = resp.getWriter();
        // 如果HttpSeesion是新建的话
        if (session.isNew()) {
            out.println("Hello,HttpSession! <br>The first response - SeesionId="
                    + sessionId + " <br>");
        } else {
            out.println("Hello,HttpSession! <br>The second response - SeesionId="
                    + sessionId + " <br>");
            // 从Session获取属性值
            out.println("The second-response - name: "
                    + session.getAttribute("name"));
        }

    }

}

/**
 * What is Session?
 * Session代表着服务器和客户端一次会话的过程。直到session失效（服务端关闭），或者客户端关闭时结束。
 * How does session works？
 * Session 是存储在服务端的，并针对每个客户端（客户），通过SessionID来区别不同用户的。Session是以Cookie技术或URL重写实现。默认以Cookie技术实现，服务端会给这次会话创造一个JSESSIONID的Cookie值。
 * 补充：
 * 其实还有一种技术：表单隐藏字段。它也可以实现session机制。这里只是作为补充，服务器响应前，会修改form表单，添加一个sessionID类似的隐藏域，以便传回服务端的时候可以标示出此会话。
 * 这技术，也可以使用在Web安全上，可以有效地控制CRSF跨站请求伪造。
 * <p>
 * ① 客户端向服务端发送第一次请求
 * 此时，客户端想让服务端把自己的名字设置到会话中。
 * ② 服务端的容器产生该用户唯一sessionID的session对象，并设置值
 * 可以从代码中看出通过从请求中req.getSession()，新生成了一个session对象。并设置了setAttribute(“name”, “Jeff”)，key为string，value是对象皆可。
 * 这时候，我们不用再把session通过cookie技术处理，容器帮我们处理了。
 * ③ 容器响应 Set-Cookie：JSESSIONID= …
 */

