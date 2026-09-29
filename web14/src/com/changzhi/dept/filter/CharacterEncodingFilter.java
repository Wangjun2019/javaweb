package com.changzhi.dept.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// 所有的请求路径都走这个过滤器。
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置请求体的字符编码方式。
        request.setCharacterEncoding("UTF-8");
        // 设置响应的内容类型以及响应的字符编码方式
        response.setContentType("text/html;charset=UTF-8");

        chain.doFilter(request, response);
    }
}