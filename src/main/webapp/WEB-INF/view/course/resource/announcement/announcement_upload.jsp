<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/6
  Time: 下午10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/course/resource/announcement/upload" method="post">
    标题：
    <input name="title" type="text" />
    <br/>
    内容：
    <input name="content" type="text" />
    <br/>
    <input name="发布" type="submit">
</form>
</body>
</html>
