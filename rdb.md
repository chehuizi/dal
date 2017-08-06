# 分库分表
  ![分库分表框架图](http://dangdangdotcom.github.io/sharding-jdbc/img/architecture.png)
  ## JDBC规范重写
  ![sharding分库分表](https://github.com/chehuizi/dal/blob/master/jdbc-interface.jpg)

# JDBC规范
jdbc规范是Java语言针对关系型数据库制定的一套接口规范。
因为Java语言是跨平台的，所以Java应用程序只要针对JDBC接口进行编程即可。
每家数据库都有自己的数据库引擎，SQL语法也有差异，Java应用程序如何做到只针对接口进行编程，而不用关心数据库的具体实现和差异呢。由每家数据库厂家提供JDBC数据库驱动，Java应用程序注册相应数据库的驱动，具体的数据库操作由驱动完成。
