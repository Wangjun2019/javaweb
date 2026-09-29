package com.changzhi.thymeleaf.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

// 监听器：项目启动初始化模板引擎
@WebListener
public class ThymeleafInitializer2 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 服务器启动阶段，就是为了创建一个”模板引擎“对象
        // 获取ServletContext对象
        ServletContext application = sce.getServletContext();

        // 创建Thymeleaf与Servlet的适配器，用它处理Web请求
        // 适配器，可以认为是Thymeleaf与Servlet整合的桥梁对象
        JakartaServletWebApplication jakartaServletWebApplication = JakartaServletWebApplication.buildApplication(application);
        // 传入适配器，创建模板解析器
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(jakartaServletWebApplication);

        // 设置模板文件存放的基础路径
        templateResolver.setPrefix("/WEB-INF/thymeleaf/");
        // 设置模板文件的后缀名
        templateResolver.setSuffix(".abc");
        // ✅模板读取编码UTF-8
        templateResolver.setCharacterEncoding("UTF-8");
        // 指定解析模式为HTML，启用HTML特有语法支持
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 禁用模板缓存（开发环境建议关闭，确保开发时修改模板立即生效）
        templateResolver.setCacheable(false);

        // 创建模板引擎（用来解析模板文件的）
        TemplateEngine templateEngine = new TemplateEngine();
        // 模板引擎关联模板解析器
        templateEngine.setTemplateResolver(templateResolver);

        // 把适配器保存到ServletContext，供后续请求使用
        application.setAttribute("jakartaServletWebApplication", jakartaServletWebApplication);
        // 把模板引擎保存到ServletContext，供所有Servlet共享使用
        application.setAttribute("templateEngine", templateEngine);
    }
}
/**
 * 1. 创建一个适配器
 * 2. 创建一个解析器
 * 3. 创建一个模板引擎（就可以解析模板文件了）
 */