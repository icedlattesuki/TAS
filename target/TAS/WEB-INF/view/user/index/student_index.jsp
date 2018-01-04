<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="course" type="com.se.global.domain.Course"--%>
<%--@elvariable id="courses" type="java.util.ArrayList<Course>"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<html>
<head>
    <title>User_Student_Index | TAS </title>
    <link rel="stylesheet" type="text/css" href="/css/user/index/user_index.css">

    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.css"  media="screen,projection"/>

    <link type="text/css" rel="stylesheet" href="/css/style.css">

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<header id="header" class="page-topbar">
    <!-- start header nav-->
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper">
                <a href="./index" class="brand-logo">TAS</a>
                <div class="header-search-wrapper hide-on-med-and-down sideNav-lock">
                    <i class="material-icons">search</i>
                    <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore">
                </div>
                <ul class="right hide-on-med-and-down">
                    <li>
                        <a href="/notice">
                            <i class="material-icons">
                                notifications
                                <small class="notification-badge">
                                    ${noticeTotalNum}
                                </small>
                            </i>
                        </a>
                    </li>
                    <li>
                        <a href="/user/info">
                            <i class="material-icons">
                                account_circle
                            </i>
                        </a>
                    </li>
                    <li>
                        <a href="/logout">
                            <i class="material-icons">
                                arrow_forward
                            </i>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>
<div class="row">
    <div class="col s12 m4 l4">

    </div>
</div>
<div class="row">
    <div class="col s0 m2 l2">

    </div>
    <div class="col s12 m8 l8">
        <div class="section">
            <h5>Course</h5>
        </div>
        <div class="divider">

        </div>
        <div class="section">

        </div>
        <c:forEach var="course" items="${courses}" varStatus="status">
            <c:if test="${status.index % 3 == 0}">
                <div class="row">
            </c:if>
                <div class="col s12 m4 l4">
                    <div class="card">
                        <div class="card-content black-text">
                            <span class="card-title">${course.name}</span>
                            <p>
                                ${course.semester += " " += course.time}
                            </p>
                            <p>
                                ${course.place}
                            </p>
                            <p>
                                ${course.credit}学分
                            </p>
                            <p>
                                ${course.college}
                            </p>
                        </div>
                        <div class="card-action">
                            <a href="/course/${course.id}" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">进入</a>
                        </div>
                    </div>
                </div>
            <c:if test="${(status.index + 1) % 3 == 0}">
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="col s0 m2 l2">
    </div>
</div>
<!--
<footer class="page-footer">
    <div class="container">
        <div class="row">
        <div class="col l6 s12">
            <h5 class="white-text">反馈</h5>
            <p class="grey-text text-lighten-4">
                您可以通过发送邮件到daoyiwu1997@gmail.com
            </p>
            <p class="grey-text text-lighten-4">
                给我们提供宝贵的建议和意见，谢谢！
            </p>
            <a href="https://mail.google.com" class="btn waves-effect waves-light cyan lighten-3">联系我们</a>
        </div>
        <div class="col l4 offset-l2 s12">
            <h5 class="white-text">友情链接</h5>
            <ul>
            <li><a class="grey-text text-lighten-3" href="http://www.cc98.org/">CC98论坛</a></li>
            <li><a class="grey-text text-lighten-3" href="http://www.cs.zju.edu.cn/">浙江大学计算机科学与技术学院</a></li>
            <li><a class="grey-text text-lighten-3" href="http://jwbinfosys.zju.edu.cn/default2.aspx">浙江大学现代教务管理系统</a></li>
            <li><a class="grey-text text-lighten-3" href="https://www.zjubtv.com/">浙江大学广播电视网</a></li>
            <li><a class="grey-text text-lighten-3" href="https://pintia.cn/">拼题A</a></li>
            </ul>
        </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
        © 2017 Copyright G5
        <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>
</footer>-->


<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
</body>
</html>