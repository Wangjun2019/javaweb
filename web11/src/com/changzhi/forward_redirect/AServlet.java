package com.changzhi.forward_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/a")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request对象：请求对象
        User user = new User("jack", 30);

        // 将User对象绑定到request域当中。
        // request域比较小：request对象是一次请求一个对象。
        // 我们之前学习过一个域对象：ServletContext【application：应用域，这个范围比较大，所有用户共享的可以绑定到这个域中】
        request.setAttribute("userObj", user);

        // 从request域中取出。
        Object obj = request.getAttribute("userObj");
        // 输出到浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("请求域当中的数据：" + obj);
    }
}
