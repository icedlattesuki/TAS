<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/2
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/course/resource-upload/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="上传" />
</form>
</body>
</html>
