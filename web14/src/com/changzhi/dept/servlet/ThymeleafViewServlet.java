package com.changzhi.dept.servlet;

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

// 这个Servlet是专门用来处理响应的。负责页面展示的。
@WebServlet("/view")
public class ThymeleafViewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应的内容类型和字符编码方式。
        resp.setContentType("text/html;charset=UTF-8");

        // 从 request域中获取 模板 的名字。（为转发准备的）
        String template = (String)req.getAttribute("template");
        if(template == null){
            // 从查询参数上获取（为重定向准备的）
            template = req.getParameter("template");
        }

        // 从 ServletContext中获取绑定的适配器和模板引擎。
        ServletContext application = getServletContext();
        JakartaServletWebApplication jakartaServletWebApplication = (JakartaServletWebApplication)application.getAttribute("jakartaServletWebApplication");
        TemplateEngine templateEngine = (TemplateEngine)application.getAttribute("templateEngine");
        WebContext webContext = new WebContext(jakartaServletWebApplication.buildExchange(req, resp), req.getLocale());

        templateEngine.process(template, webContext, resp.getWriter());
    }
}