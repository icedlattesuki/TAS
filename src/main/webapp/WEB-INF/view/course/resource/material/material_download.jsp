<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/2
  Time: 下午2:28
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="material" type="com.se.course.resource.material.domain.Material"--%>
<%--@elvariable id="materials" type="java.util.ArrayList<com.se.course.resource.material.domain.Material>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>${info}</h4>
<ul>
    <c:forEach var="material" items="${materials}">
        <li>
            <a href="/course/resource/material/download?file_id=${material.id}">${material.name}</a>, ${material.size1}
            <br/>
            <c:if test="${user.type == 2}">
                <a href="/course/resource/material/delete?file_id=${material.id}">删除</a>
            </c:if>
        </li>
    </c:forEach>
</ul>
</body>
</html>
