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

@WebServlet("/student")
public class StudentDB extends HttpServlet {
    //内存集合，代替数据库
    /**
     * 优点：
     * 1. **遍历读操作不加锁**，读多写少（页面刷新查询多，增删改少）性能好
     * 2. 遍历的时候不会报并发修改异常
     * 3. 用法和 ArrayList 一模一样，原有代码一行都不用改
     * > 非常适合网页 CRUD 内存版 Demo
     * ⚠️缺点：频繁大批量新增删除，性能较差；你这种小练习完全无所谓。
     */
    public static List<Student> studentList = new CopyOnWriteArrayList<>();

    //初始化两条测试数据
    static {
        studentList.add(new Student(1,"张三",88.5));
        studentList.add(new Student(2,"李四",92.0));
    }

    /**
     * doGet = 专门负责展示页面；
     * doPost = 专门负责修改数据。
     */

    /**
     * 只读、查询展示页面，不修改集合里面任何数据
     * 就调用 printHtml 方法，就是从集合中拿数据去渲染。没其他的逻辑
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 只负责渲染页面
        printHtml(out);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String op = request.getParameter("op");

        /**
         * - `op=add` → 告诉后端：这次 POST 请求，执行【新增】
         * - `op=update` → 告诉后端：这次 POST 请求，执行【修改】
         * - `op=delete` → 告诉后端：这次 POST 请求，执行【删除】
         */
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
        //操作完成重定向，刷新页面(防止表单重复提交)
        response.sendRedirect("student");
    }


    /**
     * 1. 拿到 `studentList` 内存集合，遍历里面每一条学生数据
     * 2. 一行一行**手动拼接 HTML 字符串**（`<table>`、`<tr>`、`<td>`、表单标签），把集合里学生 ID、姓名、分数填进去
     * 3. 通过 `PrintWriter out`，把拼接好的一整段 HTML 文本**输出写给浏览器**
     * 4. 浏览器收到 HTML 源码，浏览器内核解析标签，渲染出可视化网页表格
     */
    private void printHtml(PrintWriter out){
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>学生管理</title></head>");
        out.println("<body>");

        //新增表单，POST提交
        out.println("<h3>新增学生</h3>");
        out.println("<form action='student?op=add' method='post'>");
        out.println("学生ID：<input type='number' name='studentId' required> <br>");
        out.println("学生姓名：<input type='text' name='studentName' required> <br>");
        out.println("分数：<input type='number' step='0.1' name='score' required> <br>");
        out.println("<input type='submit' value='提交新增'>");
        out.println("</form>");
        out.println("<hr>");

        //学生列表表格
        out.println("<h3>学生列表</h3>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><th>学生ID</th><th>学生姓名</th><th>分数</th><th>操作</th></tr>");

        for (Student stu : studentList){
            out.println("<tr>");
            out.println("<td>"+stu.getStudentId()+"</td>");
            out.println("<td>"+stu.getStudentName()+"</td>");
            out.println("<td>"+stu.getScore()+"</td>");
            out.println("<td>");

            // 删除：内嵌表单 POST提交（不再用a标签GET删除）
            out.println("<form action='student?op=delete' method='post' style='display:inline' onsubmit='return confirm(\"确定删除?\")'>");
            out.println("<input type='hidden' name='id' value='"+stu.getStudentId()+"'>");
            out.println("<input type='submit' value='删除'>");
            out.println("</form>");
            out.println("&nbsp;&nbsp;");

            //修改保存表单 POST提交，表格行内输入框直接编辑
            out.println("<form action='student?op=update' method='post' style='display:inline'>");
            out.println("<input type='hidden' name='studentId' value='"+stu.getStudentId()+"'>");
            out.println("<input type='text' name='studentName' value='"+stu.getStudentName()+"' size='6'>");
            out.println("<input type='number' step='0.1' name='score' value='"+stu.getScore()+"' size='4'>");
            out.println("<input type='submit' value='保存'>");
            out.println("</form>");

            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
        out.flush();
        out.close();
    }
    /**
     *
     * ### 原先纯 Servlet 的痛点
     * 就是你现在 `printHtml` 的方式：**Java代码里面硬拼接一大堆HTML字符串**。
     * 所有页面标签都要用 `out.print()` 一行一行输出，写网页非常痛苦：标签写错、引号转义、前端页面改一点就要去改Java代码，Java和页面代码全部混在一起。
     *
     * ### JSP本质就是用来解决这个问题的优化方案
     * 1. **调转主次**
     *     - Servlet：以Java代码为主，HTML只是输出的一段字符串
     *     - JSP：**以HTML页面为主**，你可以直接在页面上写正常的html标签；需要输出动态数据的时候，才嵌入一小段Java代码。
     * 2. **职责拆分**
     *     Servlet 专心做业务：查集合、增删改、判断逻辑、跳转。
     *     JSP 专心做页面渲染：展示数据、排版页面。
     *     不再把HTML拼接写进Servlet的Java方法里面。
     *
     * 3. **底层真相**
     * JSP 最终部署到服务器，Tomcat 仍然**会把JSP自动翻译成一个Servlet**。最后还是输出HTML字符串给浏览器。
     * 所以它不是换掉Servlet，而是给了你一种更舒服、更优雅的**页面输出写法**，优化了“Java里面拼HTML”这个糟糕的过程。
     *
     * ### 通俗一句话总结
     * Servlet硬拼HTML：Java里面写网页。
     * JSP：网页里面写Java。
     *
     * 后面MVC模式，就是更进一步：连JSP里面的Java代码也要尽量删掉，数据由Servlet传给页面，JSP只用EL表达式展示。
     */
}
