<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2017/12/23
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上交列表</title>
</head>
<body>
<c:forEach var="uploadHomework" items="${uploadHomeworkList}">
    <div>
        <p>${uploadHomework.studentId}</p>
        <p>${uploadHomework.studentName}</p>
        <c:if test="${uploadHomework.uploadFileId != -1}">
            <a href="/course/homework/download?file_id=${uploadHomework.uploadFileId}">${uploadHomework.uploadFileName}</a>
        </c:if>
        <c:if test="${uploadHomework.uploadFileId == -1}">
            <p>还未提交</p>
        </c:if>
    </div>
</c:forEach>
</body>
</html>
