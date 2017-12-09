<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/9
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="courseNotice" type="com.se.notice.domain.CourseNotice"--%>
<%--@elvariable id="courseNoticeList" type="java.util.ArrayList<CourseNotice>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="courseNotice" items="${courseNoticeList}" varStatus="status">
        <li>${courseNotice.course.name},${courseNotice.totalNumber}条未读消息,<a href="/notice/course?course_notice_index=${status.index}">点击查看</a></li>
    </c:forEach>
</ul>
</body>
</html>
