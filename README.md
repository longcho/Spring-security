**Spring Security相关**

**启动过程**

1.引入依赖
```
<dependency>
	<groupId>com.imooc.security</groupId>
	<artifactId>imooc-security-browser</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>
```
2.配置系统(参见 application-example.properties)

3.增加UserDetailsService接口实现

4.如果需要记住我功能，需要创建数据库表(见 db.sql)

5.如果需要社交登录功能，需要以下额外的步骤
1).配置appId和appSecret
2).创建并配置用户注册页面，并实现注册服务(需要配置访问权限)，需要在服务中要调用ProviderSignInUtils的doPostSignUp方法。
3).添加SocialUserDetailsService接口实现
4).创建社交登录用的表 ( db.sql)

**自定义功能拓展**
向spring容器注入接口的实现替换默认的处理逻辑

1.密码加密
org.springframework.security.crypto.password.PasswordEncoder

2.用户信息读取逻辑（如数据库）
org.springframework.security.core.userdetails.UserDetailsService

3.QQ,微信登录用户信息读取逻辑
org.springframework.social.security.SocialUserDetailsService

4.Session失效时的处理
org.springframework.security.web.session.InvalidSessionStrategy

5.并发登录导致前一个session失效时的处理
org.springframework.security.web.session.SessionInformationExpiredStrategy

6.退出时的处理逻辑
org.springframework.security.web.authentication.logout.LogoutSuccessHandler

7.短信发送的处理逻辑（已实现控制台打印短信）
com.imooc.security.core.validate.code.sms.SmsCodeSender

8.向spring容器注册名为imageValidateCodeGenerator的bean，可以替换默认的图片验证码生成逻辑,bean必须实现以下接口
com.imooc.security.core.validate.code.ValidateCodeGenerator

9.当spring容器中有下面这个接口的实现时，则在社交登录的方法注册时（第一次登录），用此接口的实现自动注册用户，不会跳到注册页面
org.springframework.social.connect.ConnectionSignUp


[后台管理系统地址](longzzai.cn/manage.html)
管理员 admin  密码 123456
