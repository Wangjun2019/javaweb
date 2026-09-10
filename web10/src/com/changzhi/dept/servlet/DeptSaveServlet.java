package com.changzhi.dept.servlet;

import com.changzhi.dept.dao.DeptDao;
import com.changzhi.dept.dao.impl.DeptDaoImpl;
import com.changzhi.dept.entity.Dept;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/save")
public class DeptSaveServlet extends HttpServlet {
    private DeptDao dao = new DeptDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // post请求，在请求体中提交的数据
        // 获取当前部门编号（最大值）
        Integer maxId = dao.selectMaxId();
        System.out.println(maxId);
        int deptNo = maxId + 1;

        // 获取表单提交的数据
        String deptName = request.getParameter("deptName-changzhi");
        String location = request.getParameter("location-changzhi");

        // 封装一个部门对象
        Dept dept = new Dept();
        dept.setDeptNo(deptNo);
        dept.setDeptName(deptName);
        dept.setLoc(location);

        // 调用dao保存数据
        int count = dao.insert(dept);
        if (count > 0) {
            // 保存成功之后，重定向到list列表页面
            response.sendRedirect(request.getContextPath() + "/list");
        }
    }
}
