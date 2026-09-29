package com.changzhi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
@WebServlet("/wel")
public class WelcomeServlet extends GenericServlet {

    @Override
    public void init(){
        System.out.println("WelcomeServlet init");
    }


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().print("<h1>欢迎你！</h1>");
    }
}
