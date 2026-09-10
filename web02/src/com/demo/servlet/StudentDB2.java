package com.demo.servlet;

import com.demo.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/student2")
public class StudentDB2 extends HttpServlet {
    public static List<Student> studentList = new CopyOnWriteArrayList<>();

    //初始化两条测试数据
    static {
        studentList.add(new Student(1,"张三",88.5));
        studentList.add(new Student(2,"李四",92.0));
    }

    // TODO 换成JSP
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.把集合存入request域，传给jsp页面
        request.setAttribute("stuList", studentList);
        // 2.请求转发，跳转到jsp页面展示，不再自己打印HTML
        request.getRequestDispatcher("/student.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String op = request.getParameter("op");

        if("add".equals(op)){
            //新增学生
            Integer id = Integer.parseInt(request.getParameter("studentId"));
            String name = request.getParameter("studentName");
            Double score = Double.parseDouble(request.getParameter("score"));
            studentList.add(new Student(id,name,score));

        }else if("update".equals(op)){
            //修改学生
            Integer id = Integer.parseInt(request.getParameter("studentId"));
            String name = request.getParameter("studentName");
            Double score = Double.parseDouble(request.getParameter("score"));
            for (Student s : studentList){
                if(s.getStudentId().equals(id)){
                    s.setStudentName(name);
                    s.setScore(score);
                    break;
                }
            }

        }else if("delete".equals(op)){
            //删除学生（现在走POST！不再是GET删除，规范）
            Integer delId = Integer.parseInt(request.getParameter("id"));
            Iterator<Student> it = studentList.iterator();
            while(it.hasNext()){
                Student s = it.next();
                if(s.getStudentId().equals(delId)){
                    it.remove();
                }
            }
        }
        // 这句代码的本质：**服务器给浏览器返回一条 302 重定向响应指令**，告诉浏览器：
        // 你不要再停留在当前这个 POST 页面了，请你**重新发起一条全新的 GET 请求**，去访问地址 `student`
        response.sendRedirect("student");
    }
}
