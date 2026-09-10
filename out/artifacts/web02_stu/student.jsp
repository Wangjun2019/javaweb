<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.entity.Student" %>

<--
    Tomcat 编译 JSP 的时候，自动把它翻译成 Servlet，内部也是调用 `out.print()` 输出网页。

    👉**所以底层：JSP ≈ 自动帮你生成了一份 printHtml 代码。**
    从浏览器视角，收到的 HTML 页面完全看不出区别。
-->

<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理(JSP版)</title>
</head>
<body>
<h3>新增学生</h3>
<form action="student?op=add" method="post">
    学生ID：<input type="number" name="studentId" required> <br>
    学生姓名：<input type="text" name="studentName" required> <br>
    分数：<input type="number" step="0.1" name="score" required> <br>
    <input type="submit" value="提交新增">
</form>
<hr>

<h3>学生列表</h3>
<table border="1" cellpadding="5">
    <tr>
        <th>学生ID</th>
        <th>学生姓名</th>
        <th>分数</th>
        <th>操作</th>
    </tr>
    <%
        List<Student> stuList = (List<Student>) request.getAttribute("stuList");
        if(stuList != null){
            for(Student stu : stuList){
    %>
    <tr>
        <td><%=stu.getStudentId()%></td>
        <td><%=stu.getStudentName()%></td>
        <td><%=stu.getScore()%></td>
        <td>
            <form action="student?op=delete" method="post" style="display:inline" onsubmit="return confirm('确定删除？')">
                <input type="hidden" name="id" value="<%=stu.getStudentId()%>">
                <input type="submit" value="删除">
            </form>
            &nbsp;&nbsp;
            <form action="student?op=update" method="post" style="display:inline">
                <input type="hidden" name="studentId" value="<%=stu.getStudentId()%>">
                <input type="text" name="studentName" value="<%=stu.getStudentName()%>" size="6">
                <input type="number" step="0.1" name="score" value="<%=stu.getScore()%>" size="4">
                <input type="submit" value="保存">
            </form>
        </td>
    </tr>
    <% }} %>
</table>
</body>
</html>
