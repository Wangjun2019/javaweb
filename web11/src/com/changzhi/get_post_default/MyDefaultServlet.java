package com.changzhi.get_post_default;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 注意：注解方式无法配置url-pattern="/"，我们用web.xml注册
public class MyDefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("""
                <h2>✅ 进入我自定义的DefaultServlet</h2>
                <p>请求路径：%s</p>
                <p>说明：没有其他Servlet匹配这个地址</p>
                <p>⚠ 重点：此时 index.html / a.jpg 静态资源不会自动读取！</p>
                """.formatted(request.getRequestURI()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
