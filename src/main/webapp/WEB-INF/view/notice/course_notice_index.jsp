<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/9
  Time: 上午11:20
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="detailNotice" type="com.se.notice.domain.DetailNotice"--%>
<%--@elvariable id="detailNoticeList" type="java.util.ArrayList<DetailNotice>"--%>
<%--@elvariable id="noticeType" type="String[]"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="detailNotice" items="${detailNoticeList}" varStatus="status">
        <li>
            ${noticeType[status.index]}:${detailNotice.totalNumber}条未读消息，<a href="/notice/course/detail?detail_notice_type=${status.index}">点击查看</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
