<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2017/12/16
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业列表</title>
</head>
<body>
<c:forEach var="homework" items="${homeworkList}">
    <div>
        <a href="/course/${course_id}/homework/${homework.id}">${homework.title}</a>
        <a href="/course/${course_id}/homework/${homework.id}/delete">删除</a>
    </div>
</c:forEach>

<c:if test="${userType == 2}">
    <a href="/course/${course_id}/homework/to-assign">上传作业</a>
</c:if>

</body>
</html>
