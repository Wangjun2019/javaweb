package com.changzhi.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class B implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("B Servlet init 。。。");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
