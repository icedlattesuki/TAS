<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/1
  Time: 下午10:09
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="announcement" type="com.se.course.announcement.domain.Announcement"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h4>${announcement.title}</h4>
    <h5>${announcement.content}</h5>
    <h5>${announcement.date}</h5>
    <a href="/course/resource/announcement/list">更多</a>
</div>
<a href="/course/resource/material/to-download">资料下载</a>
<a href="/course/resource/video/watch">视频观看</a>
<a href="/course/comment">留言</a>
</body>
</html>
