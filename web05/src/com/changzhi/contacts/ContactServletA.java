package com.changzhi.contacts;

import java.io.PrintWriter;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/contactsA")
public class ContactServletA extends HttpServlet {

    // 工具方法：读取请求体完整JSON字符串
    private String readRequestBody(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    // 处理 GET 请求：查询所有 或 查询单个
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            // 查询单个：GET /contacts?id=1
            Contact contact = ContactRepository.getById(Integer.parseInt(idParam));
            if (contact != null) {
                out.write("{\n" +
                        "  \"id\": " + contact.getId() + ",\n" +
                        "  \"name\": \"" + contact.getName() + "\",\n" +
                        "  \"phone\": \"" + contact.getPhone() + "\",\n" +
                        "  \"email\": \"" + contact.getEmail() + "\"\n" +
                        "}\n");
            } else {
                resp.setStatus(404);
                out.write("{\n" +
                        "  \"error\": \"联系人不存在\"\n" +
                        "}\n");
            }
        } else {
            // 查询所有：GET /contacts
            StringBuilder json = new StringBuilder("[\n");
            boolean first = true;
            for (Contact c : ContactRepository.getAll()) {
                if (!first) {
                    json.append(",\n");
                }
                first = false;
                json.append("  {\n")
                    .append("    \"id\": ").append(c.getId()).append(",\n")
                    .append("    \"name\": \"").append(c.getName()).append("\",\n")
                    .append("    \"phone\": \"").append(c.getPhone()).append("\",\n")
                    .append("    \"email\": \"").append(c.getEmail()).append("\"\n")
                    .append("  }");
            }
            json.append("\n]\n");
            out.write(json.toString());
        }
    }

    // POST 新增：接收JSON Body，和截图curl对应
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        // 1. 读取前端传的JSON请求体
        String jsonStr = readRequestBody(req);
        // 2. JSON转Contact对象
        Contact newContact = JSON.parseObject(jsonStr, Contact.class);

        // 简单非空校验
        if (newContact.getName() == null || newContact.getPhone() == null) {
            resp.setStatus(400);
            out.write("{\"error\":\"姓名、手机号不能为空\"}\n");
            return;
        }

        // 3. 新增入库
        ContactRepository.add(newContact);
        resp.setStatus(201);
        out.write("{\n" +
                "  \"message\": \"添加成功\",\n" +
                "  \"id\": " + newContact.getId() + "\n" +
                "}\n");
    }

    // PUT 更新：URL带?id，Body传JSON修改字段，匹配截图curl
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        // 1. 从URL参数拿要更新的id
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(400);
            out.write("{\"error\":\"URL必须携带id参数\"}\n");
            return;
        }
        int targetId;
        try {
            targetId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            out.write("{\"error\":\"id必须是数字\"}\n");
            return;
        }

        // 2. 查询原有数据
        Contact existing = ContactRepository.getById(targetId);
        if (existing == null) {
            resp.setStatus(404);
            out.write("{\n" +
                    "  \"error\": \"联系人不存在\"\n" +
                    "}\n");
            return;
        }

        // 3. 读取Body里的JSON更新字段
        String jsonStr = readRequestBody(req);
        Contact updateInfo = JSON.parseObject(jsonStr, Contact.class);

        // 有值才覆盖更新，不传的字段保留原有数据
        if (updateInfo.getName() != null) existing.setName(updateInfo.getName());
        if (updateInfo.getPhone() != null) existing.setPhone(updateInfo.getPhone());
        if (updateInfo.getEmail() != null) existing.setEmail(updateInfo.getEmail());

        // 4. 执行更新
        ContactRepository.update(existing);
        out.write("{\n" +
                "  \"message\": \"更新成功\"\n" +
                "}\n");
    }


    // 处理 DELETE 请求：删除联系人
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String idParam = req.getParameter("id");
        if (idParam != null) {
            ContactRepository.delete(Integer.parseInt(idParam));
            resp.getWriter().write("{\n" +
                    "  \"message\": \"删除成功\"\n" +
                    "}\n");
        } else {
            resp.setStatus(400);
            resp.getWriter().write("{\n" +
                    "  \"error\": \"请提供要删除的ID\"\n" +
                    "}\n");
        }
    }
}