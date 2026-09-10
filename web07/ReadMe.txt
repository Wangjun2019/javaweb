> ✅ 完整时序一步一步拆解
1. Tomcat 通过反射，**new 子类对象（HelloServlet）**
    ```java
    // Tomcat底层伪代码
    HelloServlet servlet = new HelloServlet();
    ```
    > 对象内存：HelloServlet 对象里面，同时包含父类 GenericServlet 的所有成员（那个私有成员变量 `private ServletConfig config;`），此时这个 `config=null`

2. Tomcat 创建出 `ServletConfig` 对象（容器干的活）
3. Tomcat 调用：`servlet.init(config);`

4. 现在，你的子类 HelloServlet 没有重写 init(ServletConfig config)
    → 就执行从父类 GenericServlet 继承过来的 init 方法：
    ```java
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        // 这里的this：指的就是子类HelloServlet对象！
        // 把Tomcat传进来的config，保存到父类继承过来的成员变量
    }
    ```
    > `this` 永远代表当前实例，也就是 HelloServlet 对象

5. 后面浏览器发请求，Tomcat 调用 `service()`
6. 在 service 里面执行 `getServletConfig()`
    调用父类的方法：
    ```java
    public ServletConfig getServletConfig() {
        return config;
    }
    ```
    返回刚才存好的对象，拿到成功，**不为 null**

# 最容易踩坑的反例（重写init却忘super）
如果你子类写了：
```java
@Override
public void init(ServletConfig config) throws ServletException {
    // 没有 super.init(config);
    // 父类的成员变量 config 一直是 null！！
}
```
父类的 init 压根就不会跑，父类里面那个私有变量从来没赋值，后面 `getServletConfig()` 就返回 null。

# 精简版总结
1. 创建的对象：**子类实例（HelloServlet）**
2. ServletConfig：**Tomcat生成，不是父类生成**
3. 子类没有重写init → 执行继承来的父类init，把Tomcat给的config保存进对象
4. service中调用继承来的`getServletConfig()`取出保存好的值。

