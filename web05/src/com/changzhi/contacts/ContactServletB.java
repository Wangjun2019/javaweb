package com.changzhi.contacts;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

// 映射访问路径为 /contacts
@WebServlet("/contactsB")
public class ContactServletB extends HttpServlet {

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
                        "}");
            } else {
                resp.setStatus(404);
                out.write("{\n" +
                        "  \"error\": \"联系人不存在\"\n" +
                        "}");
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
            json.append("\n]");
            out.write(json.toString());
        }
    }

    // 处理 POST 请求：新增联系人
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Contact newContact = new Contact();
        newContact.setName("王五"); 
        newContact.setPhone("13700137000");
        newContact.setEmail("wangwu@example.com");
        
        ContactRepository.add(newContact);
        resp.setStatus(201);
        resp.getWriter().write("{\n" +
                "  \"message\": \"添加成功\",\n" +
                "  \"id\": " + newContact.getId() + "\n" +
                "}");
    }

    // 处理 PUT 请求：更新联系人
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Contact existing = ContactRepository.getById(Integer.parseInt(idParam));
            if (existing != null) {
                existing.setPhone("13600136000");
                ContactRepository.update(existing);
                resp.getWriter().write("{\n" +
                        "  \"message\": \"更新成功\"\n" +
                        "}");
            } else {
                resp.setStatus(404);
                resp.getWriter().write("{\n" +
                        "  \"error\": \"联系人不存在\"\n" +
                        "}");
            }
        }
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
                    "}");
        } else {
            resp.setStatus(400);
            resp.getWriter().write("{\n" +
                    "  \"error\": \"请提供要删除的ID\"\n" +
                    "}");
        }
    }
}