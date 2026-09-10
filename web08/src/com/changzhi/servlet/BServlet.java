package com.changzhi.servlet;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/b")
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("BServlet ===> " + this.getServletContext());

        // 从Servlet上下文中取出绑定的数据。
        ServletContext application = this.getServletContext();
        Object obj = application.getAttribute( "fdsafdsafds");
        System.out.println(obj);

        // 获取上下文初始化参数。
        Enumeration<String> names = application.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = application.getInitParameter(name);
            System.out.println(name + "--> " + value);
        }

    }
}