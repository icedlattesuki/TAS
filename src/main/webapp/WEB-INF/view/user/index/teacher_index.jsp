<%--@elvariable id="user" type="com.se.domain.User"--%>
<%--@elvariable id="course" type="com.se.domain.Course"--%>
<%--@elvariable id="courseList" type="java.util.ArrayList<Course>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浙江大学教学辅助系统</title>
</head>
<body>
<h1>${user.name}老师, 欢迎您！</h1>
<a href="/user/info">查看个人资料</a>
<img src="${user.imageLocation}" />
<ul>
    <c:forEach var="course" items="${courseList}" varStatus="status">
        <li><a href="/course/index?courseIndex=${status.index}">${course.name}</a>,${course.credit},${course.courseKey.semester},${course.courseKey.time},${course.courseKey.place}</li>
    </c:forEach>
</ul>
<a href="/logout">登出</a>
</body>
</html>
