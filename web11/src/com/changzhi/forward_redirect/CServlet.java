package com.changzhi.forward_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 接收跳转，尝试获取 request 域数据
@WebServlet("/c")
public class CServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object obj = request.getAttribute("userObj");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("request域中的对象：" + obj);
    }
}
