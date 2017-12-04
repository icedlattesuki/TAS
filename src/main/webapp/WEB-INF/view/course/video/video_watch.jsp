<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/4
  Time: 下午6:39
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="video" type="com.se.course.resource.domain.Resource"--%>
<%--@elvariable id="videoList" type="java.util.ArrayList<Video>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="video" items="${videoList}">
    <video width="320" height="240" controls>
        <source src="${video.location}" type="video/mp4">
    </video>
    <a href="/course/video/download?file_name=${video.name}">视频下载</a>
    <br />
</c:forEach>
</body>
</html>
