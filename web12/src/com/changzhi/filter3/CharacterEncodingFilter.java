package com.changzhi.filter3;


import jakarta.servlet.*;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始处理字符编码");
        // 执行下一个过滤器，没有过滤器时则执行最终的Servlet
        chain.doFilter(request, response);
        System.out.println("处理字符编码结束");
    }

    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter destroy");
    }
}
