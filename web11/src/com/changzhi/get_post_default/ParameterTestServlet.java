package com.changzhi.get_post_default;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/parameter_test")
public class ParameterTestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String[] names = request.getParameterValues("name");
        for (String n : names) {
            System.out.println("打印多参数: " + n);
        }
        String age = request.getParameter("age");
        System.out.println(age);
    }
}
