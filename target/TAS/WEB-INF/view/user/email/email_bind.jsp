<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/11/28
  Time: 下午7:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浙江大学教学辅助系统</title>
</head>
<body>
    <h4>${error}</h4>
    <form action="/user/email/to-bind" method="post">
        邮箱：
        <input name="email" type="text" />
        <br />
        <input type="submit" value="确认" />
    </form>
<a href="/index">返回</a>
</body>
</html>
