<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/2
  Time: 下午8:11
  To change this template use File | Settings | File Templates.
--%>
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
<a href="/course/resource/material/to-upload">资料上传</a>
<a href="/course/resource/material/to-download">资料下载</a>
<a href="/course/resource/video/to-upload">视频上传</a>
<a href="/course/resource/video/watch">视频观看</a>
<a href="/course/resource/announcement/to-upload">公告发布</a>
<a href="/course/comment">留言</a>
</body>
</html>
