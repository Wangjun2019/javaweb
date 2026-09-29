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

// 分工：Servlet负责数据的收集，最终将数据绑定到域对象中。展示数据交给 Thymeleaf。
// Servlet：业务处理，数据收集。
// Thymeleaf：只负责数据的展示。
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 向request域中绑定数据
        request.setAttribute("username", "lucy");

        // 交给Thymeleaf的模板引擎去渲染 "模板文件【index.abc】"
        // 交给模板引擎去处理
        response.setContentType("text/html;charset=UTF-8");
        ServletContext application = getServletContext();
        // 从上下文中获取Thymeleaf的Servlet适配器实例
        JakartaServletWebApplication jakartaServletWebApplication = (JakartaServletWebApplication)application.getAttribute("jakartaServletWebApplication");
        // 从上下文中获取Thymeleaf模板引擎实例
        TemplateEngine templateEngine = (TemplateEngine) application.getAttribute("templateEngine");
        // 创建Thymeleaf上下文对象，包装当前请求/响应和语言环境
        // buildExchange() 把 request/response 包装成 Thymeleaf 的 IWebExchange 接口对象（Thymeleaf不依赖Servlet API）
        // request.getLocale() 获取浏览器期望的语言偏好
        // WebContext 是 Thymeleaf 的 Web 环境上下文对象，它承载了一次请求所需的所有数据和工具，供模板引擎渲染时使用。
        WebContext webContext = new WebContext(jakartaServletWebApplication.buildExchange(request, response), request.getLocale());
        // 将数据绑定到当前请求对应的web上下文对象中。
        webContext.setVariable("username", "tom");
        // 使用模板引擎渲染名为"hello"的模板，结果写入响应输出流
        templateEngine.process("index", webContext, response.getWriter());
    }
}