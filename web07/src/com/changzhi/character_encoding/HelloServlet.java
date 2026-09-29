package com.changzhi.character_encoding;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/hello233")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 没有这行，会出现响应乱码
        //response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>你好！Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
/**
 * response.setContentType("text/html;charset=utf-8") 干两件事：
 *      底层自动调用 response.setCharacterEncoding("utf-8")，设置服务器输出流（PrintWriter）用 UTF-8 编码把文字转字节；
 *      在 HTTP 响应头 Content-Type 带上 charset=utf-8，告诉浏览器：拿到这份网页，要用 UTF-8 来解码展示。
 *
 *      ⚠ 关键铁律：必须在 getWriter () 之前调用！一旦拿到 PrintWriter，编码就锁定，后面再写编码代码无效。
 */