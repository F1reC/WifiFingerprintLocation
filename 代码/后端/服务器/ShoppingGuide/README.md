---
# 商超导购
### 基本功能
本项目以WiFi定位技术中的位置指纹法为基础，实现登录注册、室内定位这两大核心功能及评论区、搜索导航这两大拓展功能。
### 环境部署
#### 前端
API28，jdk1.8
#### 后端
java version"16.0.1"， MySQL8.0.27
##### 后端技术

| 技术                 | 说明                | 
| -------------------- | ------------------- |
| SpringBoot           | 容器+MVC框架        |
| MyBatis     | ORM框架             |
| MySQL| 关系型数据库|
##### 后端测试
postman
##### 前后端连接
花生壳内网穿透
### 服务器项目结构
```
├─main
│  ├─java
│  │  └─cn
│  │      └─gzx
│  │          └─shoppingguide
│  │              │      ShoppingGuideApplication.java  //启动类
│  │              │  
│  │              ├─chatroomTest   
│  │              │      Client.java  //聊天室客户端，用于测试
│  │              │     
│  │              ├─controller   
│  │              │      chatServer.java  //聊天室服务端
│  │              │      FindPathW.java  //实现路径数据的传送
│  │              │      FindPath.java  //Floyd算法计算多源的最短路径
│  │              │      Main.java  //数据库连接与算法启动
│  │              │      UserController.java  //登录注册逻辑
│  │              │      
│  │              ├─dao
│  │              │      UserDao.java
│  │              │      
│  │              ├─entity  //实体类
│  │              │      User.java  //用户类
│  │              │      
│  │              ├─service
│  │              │      UserService.java
│  │              │          
│  │              └─KNN  //定位算法
│  │                      KNN.java 
│  │                      KNNData.java  //指纹数据类
│  │                      KNNManager.java  //K近邻定位算法
│  │                      
│  └─resources
│      │      application.yml  //Spring配置文件
│      │  
│      ├─mapper                
│      │      UserDao.xml  //SQL指令
│      │      
│      ├─static
│      └─templates
└─test
    └─java
        └─cn
            └─gzx
                └─shoppingguide
                        ShoppingGuideApplicationTests.java
                        
```
### 参考

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-sql)