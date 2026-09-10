package com.changzhi.servlet;

import jakarta.servlet.*;

import java.io.IOException;

abstract class GenericServlet implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }

    // 如果以后javaweb程序员要重写init方法，尽量不要重写 init(ServletConfig servletConfig)，重写 init() 这个方法。
    void init(){}

    // 这个方法在生命周期那边没用到，但是在这里用到了。
    @Override
    public ServletConfig getServletConfig() {
        return config;
    }


    @Override
    public abstract void service(ServletRequest request, ServletResponse response) throws ServletException, IOException ;

    // 设计的这个方法的目的是：获取一个Servlet的信息。
    // 默认是返回一个空的字符串。
    // 如果说你需要返回这个Servlet的作者信息、版本信息等，可以重写该方法。
    // 如果你不需要这些信息，这个方法就可以不重写。
    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
