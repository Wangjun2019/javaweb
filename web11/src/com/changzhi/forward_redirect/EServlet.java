package com.changzhi.forward_redirect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 使用重定向跳转CServlet
@WebServlet("/e")
public class EServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("打印EServlet的request对象：" + request);
        System.out.println("打印EServlet的response对象：" + response);
        System.out.println("EServlet request哈希值：" + request.hashCode());
        System.out.println("EServlet response哈希值：" + response.hashCode());

        response.setContentType("text/html;charset=utf-8");
        // 尝试取出域对象
        User user = (User) request.getAttribute("userObj2");
        System.out.println("EServlet 获取到user：" + user);
        if(user != null){
            response.getWriter().print("成功拿到："+user.getName()+" , "+user.getAge());
        }else {
            response.getWriter().print("获取不到，null");
        }
    }
}
/**
 * 转发和重定向应该如何选择？
 * 1. 它们都可以完成资源的跳转。
 * 2. 重定向是两次请求，浏览器地址栏上的地址会发生变化。
 * 3. 转发是一次请求，浏览器地址栏上的地址不会变。
 * 4. 转发是应用内部资源的跳转，跳转时路径不需要添加应用的根路径。重定向的路径需要添加应用的根路径。
 * 5. 如果跳转的资源是WEB-INF下的资源，只能使用转发，不能使用重定向。
 * 6. 在A资源中向request域中绑定了数据，需要在B资源中从request域中获取数据，只能使用转发。
 * 7. 其他情况一律使用重定向。
 */
