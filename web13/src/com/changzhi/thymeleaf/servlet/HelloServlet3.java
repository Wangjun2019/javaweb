package com.changzhi.thymeleaf.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/hello3")
public class HelloServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ServletContext application = getServletContext();
        JakartaServletWebApplication webApp = (JakartaServletWebApplication) application.getAttribute("jakartaServletWebApplication");
        TemplateEngine templateEngine = (TemplateEngine) application.getAttribute("templateEngine");
        WebContext webContext = new WebContext(webApp.buildExchange(request, response), request.getLocale());

        // 1.普通字符串
        webContext.setVariable("name", "张三");
        // 2.数字
        webContext.setVariable("score", 85);
        // 3.布尔值
        webContext.setVariable("isVip", true);
        // 4.单个对象（简单用户对象）
        User user = new User();
        user.setUsername("小李");
        user.setAge(22);
        user.setEmail("li@test.com");
        webContext.setVariable("user", user);

        // 5.字符串集合
        List<String> fruitList = new ArrayList<>();
        fruitList.add("苹果");
        fruitList.add("香蕉");
        fruitList.add("橙子");
        webContext.setVariable("fruitList", fruitList);

        // 6.对象集合
        List<User> userList = new ArrayList<>();
        userList.add(new User("小王",19,"wang@test.com"));
        userList.add(new User("阿强",25,"qiang@test.com"));
        userList.add(new User("阿美",21,"mei@test.com"));
        webContext.setVariable("userList", userList);

        templateEngine.process("index3", webContext, response.getWriter());
    }

}