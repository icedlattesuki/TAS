<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/9
  Time: 上午11:36
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="messageList" type="java.util.ArrayList<String>"--%>
<%--@elvariable id="messageURL" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="message" items="${messageList}" varStatus="status">
        <li>
            <a href="${messageURL += "?message_index=" += status.index}">${message}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
