<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/14
  Time: 下午12:29
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="user" type="com.se.global.domain.Teacher"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>${info}</h4>
<ul>
    <li>工号：${user.id}</li>
    <li>姓名：${user.name}</li>
    <li>学院：${user.college}</li>
    <li>职称：${user.title}</li>
    <li>邮箱：${user.email} <a href="/user/email/modify">修改</a> </li>
    <li>个性签名：${user.signature}</li>
    <li>个人简介：${user.profile}</li>
</ul>
<a href="/user/info/edit">编辑个人资料</a>
<a href="/user/password-modify">修改密码</a>
<a href="/index">返回</a>
</body>
</html>
