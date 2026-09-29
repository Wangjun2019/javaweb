package com.changzhi.filter3;


import jakarta.servlet.*;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginCheckFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始登录验证。。。");

        // 执行下一个过滤器，没有过滤器时则执行最终的Servlet
        chain.doFilter(request, response);

        System.out.println("登录验证结束。。。");
    }

    @Override
    public void destroy() {
        System.out.println("LoginCheckFilter destroy");
    }
}
