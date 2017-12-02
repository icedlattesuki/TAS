<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/2
  Time: 下午2:28
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="resource" type="com.se.course.resource.domain.Resource"--%>
<%--@elvariable id="resourceList" type="java.util.ArrayList<Resource>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="resource" items="${resourceList}">
        <li><a href="/course/resource-download/download?file_name=${resource.name}">${resource.name}</a>, ${resource.size1}</li>
    </c:forEach>
</ul>
</body>
</html>
