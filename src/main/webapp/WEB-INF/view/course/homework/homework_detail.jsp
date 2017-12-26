<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<a href="/course/homework/download?file_id=${attachment.file_id}">${attachment.name}</a><br>
<c:if test="${userType == 2}">
    <a href="/course/${homework.course_id}/homework/${homework.id}/to-update/">编辑作业</a>
</c:if>
<c:if test="${userType == 1}">
    <a href="/course/homework/download?file_id=${uploadHomework.upload_homework_file}">${uploadHomework.name}</a><br>
    <form id="uploadForm" action="/course/${homework.course_id}/homework/${homework.id}/upload" enctype="multipart/form-data" method="post">
        <label for="uploadHomework">作业上传</label>
        <input type="file" name="file" id="uploadHomework">
        <label for="uploadSubmit">上传</label>
        <input type="submit" name="submit" id="uploadSubmit">
    </form>
</c:if>

<%--文件列表待定--%>
</body>
</html>
