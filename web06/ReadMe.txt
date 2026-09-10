Connected to server
[2026-09-06 01:53:46,418] Artifact web06:life: Artifact is being deployed, please wait...
[2026-09-06 01:53:46,778] Artifact web06:life: Artifact is deployed successfully
[2026-09-06 01:53:46,778] Artifact web06:life: Deploy took 360 milliseconds
LifecycleServlet的无参数构造方法执行了
LifecycleServlet的init方法执行了
LifecycleServlet的service方法执行了
06-Sep-2026 01:53:55.998 信息 [Catalina-utility-2] org.apache.catalina.startup.HostConfig.deployDirectory 把web 应用程序部署到目录 [D:\A3_develop\tomcat-10.1.59\webapps\manager]
06-Sep-2026 01:53:56.077 信息 [Catalina-utility-2] org.apache.catalina.startup.HostConfig.deployDirectory Web应用程序目录[D:\A3_develop\tomcat-10.1.59\webapps\manager]的部署已在[79]毫秒内完成
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
LifecycleServlet的service方法执行了
D:\A3_develop\tomcat-10.1.59\bin\catalina.bat stop
Using CATALINA_BASE:   "C:\Users\19732\AppData\Local\JetBrains\IntelliJIdea2024.1\tomcat\dfefdb35-05e2-4639-8fcc-9430d792af41"
Using CATALINA_HOME:   "D:\A3_develop\tomcat-10.1.59"
Using CATALINA_TMPDIR: "D:\A3_develop\tomcat-10.1.59\temp"
Using JRE_HOME:        "C:\Users\19732\.jdks\ms-17.0.20"
Using CLASSPATH:       "D:\A3_develop\tomcat-10.1.59\bin\bootstrap.jar;D:\A3_develop\tomcat-10.1.59\bin\tomcat-juli.jar"
Using CATALINA_OPTS:   ""
06-Sep-2026 01:54:29.586 信息 [main] org.apache.catalina.core.StandardServer.await 通过关闭端口接收到有效的关闭命令。正在停止服务器实例。
06-Sep-2026 01:54:29.587 信息 [main] org.apache.coyote.AbstractProtocol.pause 暂停ProtocolHandler["http-nio-8082"]
06-Sep-2026 01:54:29.705 信息 [main] org.apache.catalina.core.StandardService.stopInternal 正在停止服务[Catalina]
06-Sep-2026 01:54:29.711 信息 [main] org.apache.coyote.AbstractProtocol.stop 正在停止ProtocolHandler ["http-nio-8082"]
06-Sep-2026 01:54:29.714 信息 [main] org.apache.coyote.AbstractProtocol.destroy 正在销毁协议处理器 ["http-nio-8082"]
LifecycleServlet的destroy方法执行了
Disconnected from server

2. 总结这个生命周期？

默认情况下服务器启动时，Servlet 对象并不会创建。
当用户发送第一次请求时：
    Tomcat 服务器会自动调用无参数构造方法创建 Servlet 对象
    并且立即调用 Servlet 对象的 init 方法完成初始化。
    然后再调用 Servlet 对象的 service 方法处理请求。
当用户发送第 2 + 次请求时：
    Tomcat 服务器立即从 web 容器中查找请求路径对应的 Servlet 对象。
    找到 Servlet 对象之后，立即调用 Servlet 对象的 service 方法处理请求。
当用户长时间没有访问，或者 web 容器关闭的时候：
    web 容器会自动调用 Servlet 对象的 destroy 方法完成销毁前的准备工作。
    最终 Servlet 对象被销毁了。

无参构造 + init 只执行一次。
service 一次请求就执行一次。
destroy 只执行一次。

3. 通过以上的测试，得知，Servlet 对象是一个单例对象（但它不符合单例模式），
Tomcat 服务器底层自动实现了一个线程池，支持多线程并发，因此 Servlet 对象是单例的，并且在多线程并发情况下是共享的。
因此 Servlet 对象存在线程安全风险。尽量不要在 Servlet 对象中定义实例变量 / 静态变量。这些变量参与修改操作之后必然存在线程安全问题。
当然，在方法当中，例如 service 中可以定义变量，局部变量不存在线程安全问题。

4. Servlet 对象的生命周期由 Tomcat 服务器（WEB 容器）来管理的，因此在 Servlet 开发中，
不允许程序员自己手动 new Servlet。因为自己 new 的 Servlet 对象 Tomcat 不负责管理它的生命周期，
很容易导致内存泄漏。Servlet 对象的创建、对象上方法何时调用，这些操作，程序员都不要干涉，因为 Tomcat 服务器它全权负责。

5. servlet 接口中的 getServletConfig 和 getServletInfo 没有在生命周期当中。

6．想象一下，用户发送第一次请求的时候，底层Tomcat服务器的伪代码？
假设用户发送的请求路径：
`http://localhost:8082/web06_life/life`

    Tomcat服务器获取到请求路径是：`/web06_life/life`
    因此Tomcat服务器会去 web06_life项目中查找 web.xml 文件
    从 web.xml文件中查找到 `/life` 对应的Servlet全限定类名：`com.changzhi.servlet.LifecycleServlet`


    接下来是反射机制，调用无参数构造方法：
    Class clazz = Class.forName("com.changzhi.servlet.LifecycleServlet");
    // 在这个位置调用了无参数的构造方法。
    Servlet servlet = (Servlet) clazz.newInstance();

    // Tomcat服务器负责创建ServletConfig对象。
    ServletConfig servletConfig = new ......();
    // 调用了构造方法会继续调用servlet对象的init方法
    servlet.init(servletConfig);

    // Tomcat服务器将request对象创建出来。
    ServletRequest request = new .....();
    // Tomcat服务器将response对象创建出来。
    ServletResponse response = new ...();
    // 调用servlet对象的service方法处理请求
    servlet.service(request, response);

总结：（1）Servlet对象、ServletConfig对象：全局只创建1份，第一次请求（或者容器启动）就创建好了，后面所有请求一直复用这同一个对象
        ServletConfig属于这个Servlet的专属配置，只初始化一次，init()方法只跑唯一一次

   （2）ServletRequest、ServletResponse：✅每一次新请求，Tomcat 都会新建一对全新的request、response对象，请求结束立刻销毁
    - 第 1 次请求：new RequestFacade() 、new ResponseFacade()
    - 第 2 次请求：**又是全新的一对**，和上次不是同一个对象
    - 请求处理完，这两个对象就丢弃、被垃圾回收，互不干扰。

7．关闭服务器的时候，Tomcat服务器伪代码？
    Tomcat服务器会找到所有的Servlet对象。
    每一个Servlet对象的destroy方法都会调用。
    最后Servlet对象内存释放。
    服务器关闭。

8．init 方法调用的时候，Servlet对象已经实例化了吗？destroy方法的调用的时候，Servlet对象被销毁了吗？
    init方法调用时，Servlet对象已经创建完成了。
    destroy方法调用时，Servlet对象还没有销毁，只是即将销毁。

9. 聊一聊Servlet中设计的三大核心方法的作用？
   构造器不要乱动，否则状态码500.
   构造器和init的作用是等价的，因此，需要写在构造器中的代码可以写在init方法中。

   init（不常用）
   init方法只在对象第一次创建时只执行一次。
   因此这个方法是JakartaEE为javaweb程序员专门准备的一个特殊时刻。
   这个特殊时刻叫做：Servlet对象初始化时刻。
   例如：在Servlet对象实例化的时候执行一段代码，这段代码写到init方法中。

   service（最核心的，最常用的）
   一次请求，则执行一次service。
   service是专门处理请求的。（核心业务：登录、转账....）

   destroy（不常用）
   destroy也是为我们准备一个特殊时刻：销毁时刻。
   如果你需要在Servlet对象被销毁的瞬间执行一段特殊的代码，就把这个代码写到该方法中即可。

10．Servlet和ServletConfig什么关系？
    Servlet：一个人
    ServletConfig：一个人的装备（配置）（Config：配置）
    Servlet对象的创建，ServletConfig对象的创建，都是Tomcat服务器自动完成的。
    一个Servlet对象对应一个ServletConfig对象。


