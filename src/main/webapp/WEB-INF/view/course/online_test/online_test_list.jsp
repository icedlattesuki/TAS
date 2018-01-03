<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2018/1/1
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线测试列表</title>
</head>
<body>
<c:forEach var="onlineTest" items="${onlineTests}">
    <div>
        <a href="/course/${onlineTest.course_id}/onlineTest/${onlineTest.id}">${onlineTest.title}</a>
        <p>${onlineTest.ddl_date}</p>
        <p>${onlineTest.score}</p>
        <a href="/course/${onlineTest.course_id}/onlineTest/${onlineTest.id}/delete">删除测试</a>
    </div>
</c:forEach>
</body>
</html>
