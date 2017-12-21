<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2017/12/17
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业详情</title>
</head>
<body>
<h1>${homework.title}</h1>
<p>${homework.content}</p>
<p>${homework.ddl_date}</p>
<p>${homework.score}</p>
<a href="/course/homework/download?file_id=${attachment.file_id}">${attachment.name}</a>
<%--文件列表待定--%>
</body>
</html>
