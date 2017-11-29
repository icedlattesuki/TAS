<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/11/29
  Time: 上午10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>${error}</h4>
<form action="/updatePassword" method="post" >
    旧密码：
    <input name="oldPassword" type="password">
    <br/>
    新密码：
    <input name="newPassword1" type="password">
    <br/>
    再次输入新密码：
    <input name="newPassword2" type="password">
    <br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
