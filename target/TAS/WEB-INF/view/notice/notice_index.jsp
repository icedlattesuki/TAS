<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/9
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="course" type="com.se.global.domain.Course"--%>
<%--@elvariable id="courses" type="java.util.ArrayList<com.se.global.domain.Course>"--%>
<%--@elvariable id="noticeCourseNum" type="java.util.ArrayList<Integer>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="course" items="${courses}" varStatus="status">
        <li>${course.name},${noticeCourseNum.get(status.index)}条未读消息,<a href="/notice/${course.id}">点击查看</a></li>
    </c:forEach>
</ul>
</body>
</html>
