<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浙江大学教学辅助系统</title>
</head>
<body>
<h1>浙江大学教学辅助系统</h1>
<h4>${error}</h4>
<form method="post" action="/loginCheck">
    学号:
    <input name="id" type="text"/>
    <br/>
    密码:
    <input name="password" type="password"/>
    <br/>
    <input value="登录" type="submit" />
</form>
<a href="/passengerLogin">游客登录</a>
</body>
</html>
