package com.changzhi.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("你好！ Servlet");
        System.out.println("已发送消息！");

        // 数据库连接
        String url = "jdbc:mysql://node-11:3306/wangjun?useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "123123";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select id,name,manager_id from employees";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // 输出HTML表格
            out.write("<h2>员工列表</h2>");
            out.write("<table border='1' cellpadding='5'>");
            out.write("<tr><th>id</th><th>name</th><th>manager_id</th></tr>");

            while(rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer managerId = (Integer) rs.getObject("manager_id");

                out.write("<tr>");
                out.write("<td>"+id+"</td>");
                out.write("<td>"+name+"</td>");
                out.write("<td>"+managerId+"</td>");
                out.write("</tr>");
            }
            out.write("</table>");

        } catch (Exception e) {
            e.printStackTrace();
            out.write("数据库查询异常：" + e.getMessage());
        }finally {
            try{
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
