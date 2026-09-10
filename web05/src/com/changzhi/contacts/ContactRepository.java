package com.changzhi.contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactRepository {
    // 使用 ConcurrentHashMap 模拟数据库表，线程安全
    private static final Map<Integer, Contact> contactDB = new ConcurrentHashMap<>();
    private static int idCounter = 2; // 初始ID计数器

    // 预置几条模拟数据
    static {
        contactDB.put(1, new Contact(1, "张三", "13800138000", "zhangsan@example.com"));
        contactDB.put(2, new Contact(2, "李四", "13900139000", "lisi@example.com"));
    }

    // 获取所有联系人
    public static List<Contact> getAll() {
        return new ArrayList<>(contactDB.values());
    }

    // 根据ID获取单个联系人
    public static Contact getById(int id) {
        return contactDB.get(id);
    }

    // 新增联系人
    public static void add(Contact contact) {
        contact.setId(++idCounter);
        contactDB.put(contact.getId(), contact);
    }

    // 更新联系人
    public static void update(Contact contact) {
        contactDB.put(contact.getId(), contact);
    }

    // 删除联系人
    public static void delete(int id) {
        contactDB.remove(id);
    }
}
