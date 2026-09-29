package com.changzhi.character_encoding;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/save2")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet...");

        // request 是一个请求对象, 这个对象中封装了 "HTTP的请求协议"
        String username = request.getParameter("username");
        System.out.println("username = " + username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码方式
        response.setCharacterEncoding("utf-8");
        System.out.println("doPost...");

        // 设置"请求体"的字符编码方式为GBK
        request.setCharacterEncoding("gbk");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
    }
}
