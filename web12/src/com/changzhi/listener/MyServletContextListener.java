package com.changzhi.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * 1.编写监听器，也需要实现特定的接口。
 * 2.jakarta.servlet.ServletContextListener 这个监听器是用来监听：
 *    用来监听 ServletContext 对象的创建和销毁的。
 *    也就是说用来监听web服务器的启动和关闭。
 */
public class MyServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        // ServletContextEvent ：事件对象本身。
        ServletContext application = sce.getServletContext();
        application.setAttribute("data", "100");

        System.out.println("服务器启动了！！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        // 获取数据
        Object obj = application.getAttribute("data");
        System.out.println(obj);
        // 删除数据
        application.removeAttribute("data");

        System.out.println("服务器关闭了！！");
    }
}

