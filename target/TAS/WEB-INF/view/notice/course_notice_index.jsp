<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/9
  Time: 上午11:20
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="courseId" type="int"--%>
<%--@elvariable id="noticeType" type="String[]"--%>
<%--@elvariable id="noticeDetailNum" type="java.util.ArrayList<Integer>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="num" items="${noticeDetailNum}" varStatus="status">
        <li>
            ${noticeType[status.index]}:${num}条未读消息，<a href="${"/notice/" += courseId += "/detail?notice_type=" += status.index}">点击查看</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
