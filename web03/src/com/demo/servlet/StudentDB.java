package com.demo.servlet;

import com.demo.dao.StudentDao;
import com.demo.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stu_db")
public class StudentDB extends HttpServlet {

    //创建dao对象，用来操作数据库
    private final StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //每次查询数据库获取最新列表
        List<Student> stuList = studentDao.findAll();
        request.setAttribute("stuList",stuList);
        //转发到jsp展示
        request.getRequestDispatcher("/student.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String op = request.getParameter("op");

        if("add".equals(op)){
            Integer id = Integer.parseInt(request.getParameter("studentId"));
            String name = request.getParameter("studentName");
            Double score = Double.parseDouble(request.getParameter("score"));
            studentDao.add(new Student(id,name,score));

        }else if("update".equals(op)){
            Integer id = Integer.parseInt(request.getParameter("studentId"));
            String name = request.getParameter("studentName");
            Double score = Double.parseDouble(request.getParameter("score"));
            studentDao.update(new Student(id,name,score));

        }else if("delete".equals(op)){
            Integer delId = Integer.parseInt(request.getParameter("id"));
            studentDao.delete(delId);
        }
        //重定向，PRG模式不变，防表单重复提交
        response.sendRedirect("stu_db");
    }
}
