package com.changzhi.forward_redirect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 1. 浏览器：只发出1次 HTTP 请求 ✅
 * 2. 服务器：确实存在两个Servlet对象（AServlet 实例、BServlet 实例） ✅
 * 3. 但是request对象只有一份，两个Servlet共用这同一个 request
 *
 * ## 完整流程（forward转发场景）
 *
 * 1. 浏览器发请求访问 `/a`
 * 2. Tomcat找到`AServlet`单例对象，创建**request①、response①**
 * 3. AServlet执行代码：
 * request.getRequestDispatcher("/b").forward(request,response);
 * 4. Tomcat不会新建request、不会新建response
 * 直接把当前这一组request①、response①，传给已经存在的`BServlet`单例对象去执行`doGet`
 * > `AServlet` 和 `BServlet` 是**两个独立的Java对象（两个不同实例）
 * > 但是它们操作的request、response 是同一个对象
 */
// 使用重定向跳转CServlet
@WebServlet("/d")
public class DServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("打印DServlet的request对象：" + request);
        System.out.println("打印DServlet的response对象：" + response);

        // request对象：请求对象
        User user = new User("tom", 40);
        request.setAttribute("userObj2", user);

        request.getRequestDispatcher("/e").forward(request,response);

    }
}

