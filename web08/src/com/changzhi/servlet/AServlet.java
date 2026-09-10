package com.changzhi.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
@WebServlet("/a")
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("AServlet ===> " + this.getServletContext());
        ServletContext application = this.getServletContext();

        // 创建User对象
        User user = new User( "jack", 30);
        // 将user对象绑定到Servlet上下文对象中。
        application.setAttribute( "fdsafdsafds", user);
        System.out.println("【添加】执行setAttribute，key = fdsafdsafds");
    }
}
