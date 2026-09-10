package com.changzhi.dept.servlet;

import com.changzhi.dept.util.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/list2")
public class DeptListServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("====进入doGet====");
        PrintWriter out = response.getWriter();
        out.print("""
                <!DOCTYPE html>
                <html lang="zh-CN">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>部门管理系统 - 部门列表</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                            font-family: 'Arial', sans-serif;
                        }
                        body {
                            background-color: #f5f5f5;
                        }
                        .container {
                            max-width: 1200px;
                            margin: 0 auto;
                            padding: 20px;
                        }
                        .header {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            margin-bottom: 30px;
                        }
                        .header h1 {
                            color: #333;
                            font-size: 24px;
                        }
                        .add-btn {
                            padding: 10px 20px;
                            background-color: #4a90e2;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            text-decoration: none;
                            font-size: 14px;
                            transition: background-color 0.3s;
                        }
                        .add-btn:hover {
                            background-color: #3a7bc8;
                        }
                        .department-table {
                            width: 100%;
                            border-collapse: collapse;
                            background-color: white;
                            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
                            border-radius: 4px;
                            overflow: hidden;
                        }
                        .department-table th, .department-table td {
                            padding: 15px;
                            text-align: left;
                            border-bottom: 1px solid #eee;
                        }
                        .department-table th {
                            background-color: #f8f9fa;
                            font-weight: 600;
                            color: #555;
                        }
                        .department-table tr:hover {
                            background-color: #f8f9fa;
                        }
                        .action-btn {
                            padding: 6px 12px;
                            margin-right: 5px;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            font-size: 13px;
                            transition: all 0.3s;
                            text-decoration: none;
                            display: inline-block;
                        }
                        .view-btn {
                            background-color: #5cb85c;
                            color: white;
                        }
                        .view-btn:hover {
                            background-color: #4cae4c;
                        }
                        .edit-btn {
                            background-color: #f0ad4e;
                            color: white;
                        }
                        .edit-btn:hover {
                            background-color: #eea236;
                        }
                        .delete-btn {
                            background-color: #d9534f;
                            color: white;
                        }
                        .delete-btn:hover {
                            background-color: #d43f3a;
                        }
                        .logout {
                            text-align: right;
                            margin-top: 20px;
                        }
                        .logout a {
                            color: #777;
                            text-decoration: none;
                            font-size: 14px;
                        }
                        .logout a:hover {
                            color: #333;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>部门列表</h1>
                            <a href="" class="add-btn">添加部门</a>
                        </div>
                        <table class="department-table">
                            <thead>
                                <tr>
                                    <th>部门编号</th>
                                    <th>部门名称</th>
                                    <th>部门地理位置</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                """);
        // 连接数据库，动态打印表格的行tr
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            System.out.println("数据库连接成功 conn="+conn);

            String sql = "select deptno,dname,loc from dept";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                // System.out.println(deptno+" | "+dname+" | "+loc); //打印数据库的值

                out.print("<tr>");
                out.print(" <td>" + deptno + "</td>");
                out.print(" <td>" + dname + "</td>");
                out.print(" <td>" + loc + "</td>");
                out.print(" <td>");
                out.print(" <a href='' class='action-btn view-btn'>查看</a>");
                out.print(" <a href='' class='action-btn edit-btn'>修改</a>");
                out.print(" <a href='' class='action-btn delete-btn' onclick=''>删除</a>");
                out.print(" </td>");
                out.print("</tr>");
            }
            System.out.println("while循环结束");

        } catch (Exception e) {
            e.printStackTrace(); // 异常一定会打印在控制台
            System.out.println("出现异常");
        }

        out.print("""
                </tbody>
                        </table>
                        <div class="logout">
                            <a href="">退出登录</a>
                        </div>
                    </div>
                </body>
                </html>
                """);
    }
}