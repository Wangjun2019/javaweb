package com.changzhi.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ServletConfigTest extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取ServletConfig对象。
        ServletConfig config = this.getServletConfig();
        out.print("<h3>Servlet对象本身： " + this + "</h3>");
        out.print("<h3>Servlet对象本身的配置对象： " + config + "</h3>");

        System.out.println("=========");

        // 获取所有的初始化参数的name
        Enumeration<String> names = config.getInitParameterNames();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.print("初始化参数的名字和对应的值：" + name + " ---> ");
            String value = config.getInitParameter(name);
            System.out.println(value);
        }
        System.out.println("=========");

        // 获取所有的初始化参数的name
        Enumeration<String> names2 = this.getInitParameterNames();
        while(names2.hasMoreElements()) {
            String name = names2.nextElement();
            System.out.print("初始化参数的名字和对应的值：" + name + " ---> ");
            String value = config.getInitParameter(name);
            System.out.println(value);
        }

        // TODO ServletConfig对象都不用获取，用this就行

        // 通过config对象获取servlet的名字
        String servletName = config.getServletName();
        String  servletName2 = this.getServletName();
        System.out.println(servletName);
        System.out.println(servletName2);

        // 获取 ServletContext 对象。
        // 这个对象叫做 Servlet 上下文对象。
        ServletContext application = config.getServletContext();
        ServletContext application2 = this.getServletContext();
        out.print("<h3>application = " + application + "</h3>");
        out.print("<h3>application = " + application2 + "</h3>");



    }

}
