<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/4
  Time: 下午6:39
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="video" type="com.se.courses.resource.video.domain.Video"--%>
<%--@elvariable id="videos" type="java.util.ArrayList<Video>"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>${info}</h4>
<c:forEach var="video" items="${videos}">
    <video width="320" height="240" controls>
        <source src="${video.location}" type="video/mp4">
    </video>
    <a href="/course/${courseId}/resource/video/download?file_id=${video.id}">视频下载</a>
    <br />
    <c:if test="${user.type == 2}">
        <a href="/course/${courseId}/resource/video/delete?file_id=${video.id}">删除</a>
    </c:if>
</c:forEach>
</body>
</html>
