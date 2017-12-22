<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/course/${courseId}/resource/announcement/upload" method="post">
    标题：
    <input name="title" type="text" />
    <br/>
    内容：
    <input name="content" type="text" />
    <br/>
    <input name="发布" type="submit">
</form>
</body>
</html>
