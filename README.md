# reptile
前言:
我们使用SpringBootWeb的方式。可以直接 java -jar reptile.jar启动该项目。安装Mysql数据库  

## 安装部署
- 系统配置 
1. Java SDK 1.8
2. Mysql 5.7
3. Gradle 4.6+

- 部署
1. git clone  
2. cp ./src/main/resources/application.properties ./application.properties  
3. vim application.properties #增加自己所需的配置  
4. gradle build -x test  
5. nohup java -jar -Dspring.config.location=application.properties ./build/libs/reptile-1.0-SNAPSHOT.jar &

## 介绍
1. 爬取C5网站Dota相关饰品
2. 主要爬取饰品信息，交易记录，求购信息，出售信息4个模块

**TODO**
- [ ] http代理部分优化拆分
- [ ] 多饰品需要使用多线程优化
- [ ] socketTimeOut问题
- [ ] 无用代码删除
- [ ] 优化查询接口
- [ ] *支持其他网站*  

**其他相关项目**
- [ ] 前端显示以及K线，暂无


</br>

## 欢迎提出问题或留言
Email: yiranwenchengzhi@163.com  
如果你想加入或者建议，请发送邮件。我会第一时间回复
