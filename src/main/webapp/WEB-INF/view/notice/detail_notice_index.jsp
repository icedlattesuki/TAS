<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/9
  Time: 上午11:36
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="notice" type="com.se.notice.domain.Notice"--%>
<%--@elvariable id="notices" type="java.util.ArrayList<com.se.notice.domain.Notice>"--%>
<%--@elvariable id="noticeURL" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="notice" items="${notices}" varStatus="status">
        <li>
            <a href="${"/course/" += notice.courseId += noticeURL += "?notice_id=" += notice.id}">${notice.message}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
