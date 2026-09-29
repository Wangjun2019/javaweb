package com.changzhi.filter2;

import jakarta.servlet.*;

import java.io.IOException;

public class CommonCodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("begin: Common Code");

        // 执行下一个过滤器，没有过滤器时则执行最终的Servlet
        chain.doFilter(request, response);

        System.out.println("end: Common Code");
    }

    @Override
    public void destroy() {

    }
}
