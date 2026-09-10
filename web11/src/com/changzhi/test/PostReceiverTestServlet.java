package com.changzhi.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/save")
public class PostReceiverTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // 1. 设置请求和响应的编码，解决中文乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取表单提交的数据（使用 input 标签中的 name 属性值）
        String deptId = request.getParameter("deptId");
        String deptName = request.getParameter("deptName");
        String location = request.getParameter("location");

        // 3. 在后台控制台打印接收到的数据（模拟业务处理/存入数据库）
        System.out.println("==================================");
        System.out.println("【后台收到前端POST请求】");
        System.out.println("部门编号：" + deptId);
        System.out.println("部门名称：" + deptName);
        System.out.println("部门位置：" + location);

        // 4. 向浏览器页面反馈结果
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>数据接收成功！</h1>");
        out.println("<p>部门编号：" + deptId + "</p>");
        out.println("<p>部门名称：" + deptName + "</p>");
        out.println("<p>部门位置：" + location + "</p>");
        out.println("</body></html>");
        out.flush();
    }
}

