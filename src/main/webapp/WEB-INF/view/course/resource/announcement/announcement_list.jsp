<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/6
  Time: 下午10:56
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="announcement" type="com.se.course.announcement.domain.Announcement"--%>
<%--@elvariable id="announcementList" type="java.util.ArrayList<Announcement>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="announcement" items="${announcementList}">
    <div>
        <h4>${announcement.title}</h4>
        <h5>${announcement.content}</h5>
        <h5>${announcement.date}</h5>
    </div>
</c:forEach>
</body>
</html>
