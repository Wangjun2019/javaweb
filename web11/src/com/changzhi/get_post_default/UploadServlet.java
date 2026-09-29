package com.changzhi.get_post_default;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

// 映射访问路径 /upload
@WebServlet("/upload")
// 开启multipart文件上传支持
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1M，超过这个大小写入临时文件
        maxFileSize = 10 * 1024 * 1024,  // 单个文件上限 10MB
        maxRequestSize = 20 * 1024 * 1024 // 请求总大小上限20MB
)
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应编码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        // 获取上传的文件part，name="file" 对应前端input的name
        Part part = request.getPart("file233");

        // 获取原始文件名
        String originalFileName = part.getSubmittedFileName();
        if(originalFileName == null || originalFileName.trim().isEmpty()){
            response.getWriter().write("请选择文件！");
            return;
        }

        // 保存目录（Windows：D:/upload/；Linux改成 /opt/upload/）
        String saveBasePath = "D:/uploads/";
        File saveDir = new File(saveBasePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        // 生成不重复文件名，避免覆盖
        String newFileName = UUID.randomUUID() + "_" + originalFileName;
        String fullPath = saveBasePath + newFileName;

        // 写入磁盘
        part.write(fullPath);

        response.getWriter().write("上传成功<br>原始文件名：" + originalFileName + "<br>保存路径：" + fullPath);
    }

    // 文件上传只用POST，GET直接拒绝
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("文件上传必须使用POST请求！");
    }
}

