package com.changzhi.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends GenericServlet {



    // 如果重写父类的init方法，就把父类的init方法顶掉了。
//    @Override
//    public void init(ServletConfig config) throws ServletException {}

    /*@Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 必须要有这一行
        super.init(servletConfig);
        // 再扩展逻辑。
    }*/


    public void init(){
        System.out.println("HelloServlet init...");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 我想在 HelloServlet的service方法中获取 ServletConfig 对象？你怎么获取?
//        ServletConfig config = this.getServletConfig();
//        System.out.println(config);
//        // 为了不获取null，继续改造

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>Hello GenericServlet!</h1>");

        // 我想在 HelloServlet的service方法中获取 ServletConfig 对象？你怎么获取？
        ServletConfig config = this.getServletConfig();
        // 对象直接内存地址输出到浏览器。
        out.print("<h1>");
        out.print(config);
        out.print("</h1>");

    }


}
