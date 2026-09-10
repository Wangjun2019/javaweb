package com.changzhi.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/c")
public class CServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ServletContext application = this.getServletContext();
        // 从Servlet上下文中移除绑定。
        application.removeAttribute("fdsafdsafds");
        System.out.println("【移除】执行removeAttribute，key = fdsafdsafds");
    }
}

