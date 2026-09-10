package com.changzhi.servlet;


import jakarta.servlet.*;

import java.io.IOException;
import java.nio.charset.Charset;

public class LifecycleServlet implements Servlet {

    public LifecycleServlet() {
        System.out.println("LifecycleServlet的无参数构造方法执行了");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("LifecycleServlet的init方法执行了");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 在Tomcat服务器中，标准输出流默认采用的字符编码方式是：GBK（用的是操作系统默认的）
        String defaultCharset = Charset.defaultCharset().name();
        String consoleEncoding = System.getProperty("native.encoding");
        System.out.println("JVM默认编码 = " + defaultCharset);
        System.out.println("操作系统控制台编码 = " + consoleEncoding);



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
