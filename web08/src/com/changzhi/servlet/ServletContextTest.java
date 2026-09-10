package com.changzhi.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * 1. 什么是ServletContext？
 *     Servlet上下文对象。
 * 2. ServletContext对象在整个webapp中只有一个。
 * 3. ServletContext对象在服务器启动阶段创建，直到服务器关闭的时候销毁。
 * 4. Servlet ServletConfig ServletContext关系？
 *
 * 5. 什么情况下适合使用ServletContext？【放在这个对象中的数据，都是服务器级别的数据。不是请求级别的，不是用户级别的。】
 *     5.1 所有用户共享的数据。
 *     5.2 数据量小。
 *     5.3 对共享数据很少的修改操作。
 * */


public class ServletContextTest extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // 获取 ServletContext
        ServletContext application = this.getServletContext();
        ServletContext application2 = this.getServletConfig().getServletContext();
        System.out.println(application2 == application); // true
    }

}
