package com.changzhi.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SingletonTestServlet")
public class SingletonTestServlet extends HttpServlet {

    // 成员变量！属于Servlet对象，单例则所有请求共享这一份
    // 这里测试也就算了。千万不要在 Servlet 中定义可修改的成员变量！线程不安全！
    private Integer count = 0;

    @Override
    public void init() throws ServletException {
        System.out.println("==== init()执行了，Servlet实例被创建 ====");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置服务器输出编码
        response.setCharacterEncoding("UTF-8");
        // 告诉浏览器用UTF‑8解析
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("Servlet对象hashCode:" + this.hashCode());
        count++;
        System.out.println("收到一次请求，当前count值 = " + count);

        response.getWriter().write("当前访问次数:" + count);
    }
}
