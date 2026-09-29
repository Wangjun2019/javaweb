package com.changzhi.dept.servlet;
import com.changzhi.dept.dao.DeptDao;
import com.changzhi.dept.dao.impl.DeptDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeptDeleteServlet extends HttpServlet {
    private DeptDao dao = new DeptDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户提交的要删除的部门编号。
        String id = request.getParameter("id");
        Integer deptNo = Integer.valueOf(id);

        // 删除
         int count = dao.deleteById(deptNo);
        if(count > 0){
            // 重新查询数据库展示列表。
            // 让浏览器重新发送一次全新的请求。
            // 请求路径是: http://localhost:8082/dept/list
            // 重点中的重点：将路径响应给浏览器，浏览器拿到请求路径之后，自发性的自动的再重新向服务器发送请求。

            //response.sendRedirect("http://localhost:8082/dept/list");
            // http://localhost:8082 可以省略。【注意：项目的根路径不能省略。你就等同看做超链接就行。】
            //response.sendRedirect("/dept/list");

            response.sendRedirect(request.getContextPath() + "/list");
        }
    }
    /**
     * request.getContextPath()：动态获取项目上下文根路径（就是Tomcat Deployment里面配置的 Application context，例如/web09_dept）
     * request.getContextPath() + "/list" 拼接后 = /web10_dept/list
     * sendRedirect 返回 302 状态码，浏览器发起第二次 GET 请求访问 /web10_dept/list，跳转到列表页面。
     *
     * // 写法1：写死完整地址，不推荐，换端口/IP项目直接报错
     * response.sendRedirect("http://localhost:8082/dept/list");
     * // 写法2：写死上下文路径，项目改名、改context就失效
     * response.sendRedirect("/dept/list");
     * // 写法3【标准推荐】动态拼接，适配任意部署上下文
     * response.sendRedirect(request.getContextPath() + "/list");
     */

    /**
     *  `response.sendRedirect(路径)`
     *
     * 1. 服务器给浏览器返回 302 状态码 + 新地址**
     * 2. 浏览器自动发起第二次全新 GET 请求**访问这个地址
     * 3. 地址栏 URL 会变化
     * 4. 属于客户端跳转，原来本次request对象失效
     */
}
