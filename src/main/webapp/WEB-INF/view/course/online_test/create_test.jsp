<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2017/12/26
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建测试</title>
</head>
<body>
<form method="post">
    <div>
        <p>第一题</p>
        名称<input type="text" name="title[]"><br>
        A.<input type="text" name="a[]"><br>
        B.<input type="text" name="b[]"><br>
        C.<input type="text" name="c[]"><br>
        D.<input type="text" name="d[]"><br>
    </div>
    <div>
        <p>第二题</p>
        名称<input type="text" name="title[]"><br>
        A.<input type="text" name="a[]"><br>
        B.<input type="text" name="b[]"><br>
        C.<input type="text" name="c[]"><br>
        D.<input type="text" name="d[]"><br>
    </div>
    <div>
        <p>第三题</p>
        名称<input type="text" name="title[]"><br>
        内容<textarea name="objective_content[]"></textarea>
    </div>
    <div>
        <p>第四题</p>
        名称<input type="text" name="title[]"><br>
        内容<textarea name="objective_content[]"></textarea>
    </div>
    <input type="submit" name="提交">
</form>
</body>
</html>
