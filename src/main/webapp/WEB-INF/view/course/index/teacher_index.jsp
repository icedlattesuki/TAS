<%--@elvariable id="courseId" type="int"--%>
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
    <a href="/course/${courseId}/resource/announcement/list">更多</a>
</div>
<a href="/course/${courseId}/resource/material/to-upload">资料上传</a>
<a href="/course/${courseId}/resource/material/to-download">资料下载</a>
<a href="/course/${courseId}/resource/video/to-upload">视频上传</a>
<a href="/course/${courseId}/resource/video/watch">视频观看</a>
<a href="/course/${courseId}/resource/announcement/to-upload">公告发布</a>
<a href="/course/${courseId}/comment">留言</a>
</body>
</html>