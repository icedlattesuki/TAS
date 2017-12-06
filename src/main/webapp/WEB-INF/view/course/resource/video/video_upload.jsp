<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/4
  Time: 下午6:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/course/resource/video/upload" method="post" enctype="multipart/form-data">
    标题：
    <input type="text" name="title">
    <br/>
    简介：
    <input type="text" name="profile">
    <br/>
    文件：
    <input type="file" name="file" />
    <br/>
    <input type="submit" value="上传" />
</form>
</body>
</html>
