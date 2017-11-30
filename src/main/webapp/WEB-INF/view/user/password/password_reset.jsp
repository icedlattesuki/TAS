<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/11/29
  Time: 下午10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>${error}</h4>
<form action="/user/password/password-reset" method="post">
    学号：
    <input type="text" name="id" />
    <br />
    邮箱：
    <input type="text" name="email" />
    <br />
    <input type="submit" value="找回密码">
</form>
<a href="/login">返回</a>
</body>
</html>
