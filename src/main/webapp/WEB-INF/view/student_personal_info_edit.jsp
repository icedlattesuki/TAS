<%--@elvariable id="user" type="com.se.domain.Student"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="updatePersonalInfo" enctype="multipart/form-data" method="post">
    头像：
    <input type="file" name="image" />
    <br />
     邮箱：
    <input type="text" name="email" value="${user.email}" />
    <br/>
    个性签名：
    <input type="text" name="signature" value="${user.signature}" />
    <br/>
    个人简介：
    <input type="text" name="profile" value="${user.profile}" />
    <br/>
    <input type="submit" value="更新" />
</form>
</body>
</html>
