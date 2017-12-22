<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2017/12/16
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>布置作业</title>
</head>
<body>
<form id="homeworkForm" action="/course/${course_id}/homework/assign" enctype="multipart/form-data" method="post">
    <label for="homeworkTitle">名称</label>
    <input type="text" name="title" id="homeworkTitle"><br>
    <label for="homeworkDDL">截止日期</label>
    <input type="date" name="ddl" id="homeworkDDL"><br>
    <label for="homeworkScore">满分</label>
    <input type="number" name="score" id="homeworkScore" min="0" max="100"><br>
    <label for="homeworkContent">作业内容</label>
    <textarea name="content" id="homeworkContent"></textarea><br>
    <label for="homeworkFile">附件上传</label>
    <input type="file" name="file" id="homeworkFile"><br>
    <label for="homeworkSubmit">提交</label>
    <input type="submit" name="submit" id="homeworkSubmit"><br>
</form>
</body>
</html>
