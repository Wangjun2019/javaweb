package com.changzhi.servlet2;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

//@WebServlet("/wel2")
public class WelcomeServlet extends GenericServlet {


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().print("<h1>欢迎你！</h1>");
        // 调用ServletConfig对象的 getInitParameter()的方法，你有几种方式？
        // 第一种方式：
        String s1 = this.getInitParameter("username");

        // 第二种方式
        String s2 = this.getServletConfig().getInitParameter("username");
        System.out.println(s1);
        System.out.println(s2);
    }
}
