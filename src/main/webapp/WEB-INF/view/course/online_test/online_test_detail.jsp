<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2018/1/1
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试详情</title>
</head>
<body>
<div>
    <p>${onlineTest.title}</p>
    <p>${onlineTest.ddl_date}</p>
</div>
<c:forEach var="choiceQuestion" items="${choiceQuestions}">
    <div>
        <p>${choiceQuestion.title}</p>
        A.<p>${choiceQuestion.choice_a}</p>
        B.<p>${choiceQuestion.choice_b}</p>
        C.<p>${choiceQuestion.choice_c}</p>
        D.<p>${choiceQuestion.choice_d}</p>
    </div>
</c:forEach>
<c:forEach var="fillQuestion" items="${fillQuestions}">
    <div>
        <p>${fillQuestion.title}</p>
        <p>${fillQuestion.content}</p>
    </div>
</c:forEach>
</body>
</html>
