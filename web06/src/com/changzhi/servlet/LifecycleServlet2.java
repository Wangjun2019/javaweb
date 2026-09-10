package com.changzhi.servlet;


import jakarta.servlet.*;

import java.io.IOException;

public class LifecycleServlet2 implements Servlet {

    public LifecycleServlet2() {
        System.out.println("LifecycleServlet的无参数构造方法执行了");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // org.apache.catalina.core.StandardWrapperFacade@1b48ed85
        // jakarta.servlet.ServletConfig 这是一个接口。
        // 这个接口的实现类，在Tomcat服务器中的具体实现类: org.apache.catalina.core.StandardWrapperFacade@1b48ed85
        // Tomcat实现了Servlet规范。
        System.out.println("servletConfig对象：" + servletConfig);
        // 读取配置参数
        String username = servletConfig.getInitParameter("username");
        String password = servletConfig.getInitParameter("password");
        String pageSize = servletConfig.getInitParameter("pageSize");
        // servletConfig.getServletName();

        //控制台打印
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("pageSize = " + pageSize);

        System.out.println("LifecycleServlet的init方法执行了");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // ServletRequest是接口，Tomcat服务器提供了该接口的实现：org.apache.catalina.connector.RequestFacade
        // org.apache.catalina.connector.RequestFacade@393b8b37

        // ServletResponse是接口，Tomcat服务器提供了该接口的实现：org.apache.catalina.connector.ResponseFacade
        // org.apache.catalina.connector.ResponseFacade@3d0408c8

        System.out.println("ServletRequest对象：" + request);
        System.out.println("ServletResponse对象：" + response);


        // 当IDEA工具的控制台采用UTF‑8的方式，则会出现乱码。
        // 建议解决方案：在启动Tomcat服务器的时候，告诉tomcat服务器标准输出流使用 UTF‑8的字符编码方式。
        // 在Tomcat的JVM启动选项中添加：‑Dstdout.encoding=UTF‑8

        System.out.println("service执行了");
        System.out.println("LifecycleServlet的service方法执行了");
    }

    @Override
    public void destroy() {
        System.out.println("LifecycleServlet的destroy方法执行了");
    }

    @Override
    public String getServletInfo() {
        System.out.println("LifecycleServlet的getServletInfo方法执行了");
        return "";
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("LifecycleServlet的getServletConfig方法执行了");
        return null;
    }

}
