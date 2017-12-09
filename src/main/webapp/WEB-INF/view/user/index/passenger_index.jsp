<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="course" type="com.se.global.domain.Course"--%>
<%--@elvariable id="courseList" type="java.util.ArrayList<Course>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浙江大学教学辅助系统</title>
</head>
<body>
<h1>欢迎使用浙江大学教学辅助系统</h1>
<ul>
    <c:forEach var="course" items="${courseList}" varStatus="status">
        <li><a href="/course?currentId=${status.index}">${course.name}</a>,${course.credit},${course.courseKey.semester},${course.courseKey.time},${course.courseKey.place}</li>
    </c:forEach>
</ul>
</body>
</html>
