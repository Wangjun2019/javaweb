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

@WebServlet("/hello2")
public class HelloServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ========== 新增两行编码设置，放在最前面！ ==========
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ServletContext application = getServletContext();

        // 取出全局保存好的对象
        JakartaServletWebApplication webApp = (JakartaServletWebApplication) application.getAttribute("jakartaServletWebApplication");
        TemplateEngine templateEngine = (TemplateEngine) application.getAttribute("templateEngine");

        // 构建Thymeleaf上下文，用来存传给页面的数据
        WebContext webContext = new WebContext(webApp.buildExchange(request, response), request.getLocale());

        // 放简单字符串变量
        webContext.setVariable("username", "小明");
        webContext.setVariable("age", 20);

        // 放一个集合，用来演示 th:each 循环
        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("篮球");
        hobbyList.add("看书");
        hobbyList.add("编程");
        webContext.setVariable("hobbyList", hobbyList);

        // 渲染模板，参数写模板文件名【不要写 .html】
        templateEngine.process("index2", webContext, response.getWriter());
    }
}