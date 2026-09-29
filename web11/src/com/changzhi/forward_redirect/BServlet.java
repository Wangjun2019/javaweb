package com.changzhi.forward_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 使用重定向跳转CServlet
@WebServlet("/b")
public class BServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 跳转到BServlet
        // 在web开发中，资源跳转有两种常见方式：
        // 第一种：重定向（原理：将 /项目名/b 反馈给浏览器，浏览器自发的重新发送一次全新的请求：/web10/b）
        response.sendRedirect(request.getContextPath() + "/c");

        // 第二种：转发
    }

}
