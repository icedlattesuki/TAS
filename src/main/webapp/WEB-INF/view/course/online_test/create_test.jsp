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
<form method="post" action="/course/${courseId}/onlineTest/create">
    测试名称<input type="text" name="testTitle"><br>
    截止日期<input type="datetime-local" name="ddlDate">
    <div>
        <p>第一题</p>
        名称<input type="text" name="title"><br>
        A.<input type="text" name="a"><br>
        B.<input type="text" name="b"><br>
        C.<input type="text" name="c"><br>
        D.<input type="text" name="d"><br>
        <select name="answer">
            <option value="a">A</option>
            <option value="b">B</option>
            <option value="c">C</option>
            <option value="d">D</option>
        </select><br>
        分数.<input type="number" name="score">
    </div>
    <div>
        <p>第二题</p>
        名称<input type="text" name="title"><br>
        A.<input type="text" name="a"><br>
        B.<input type="text" name="b"><br>
        C.<input type="text" name="c"><br>
        D.<input type="text" name="d"><br>
        <select name="answer">
            <option value="a">A</option>
            <option value="b">B</option>
            <option value="c">C</option>
            <option value="d">D</option>
        </select><br>
        分数.<input type="number" name="score">
    </div>
    <div>
        <p>第三题</p>
        名称<input type="text" name="title"><br>
        内容<textarea name="objectiveContent"></textarea>
        分数<input type="number" name="score">
    </div>
    <div>
        <p>第四题</p>
        名称<input type="text" name="title"><br>
        内容<textarea name="objectiveContent"></textarea>
        分数<input type="number" name="score">
    </div>
    <input type="submit" name="提交">
</form>
</body>
</html>
