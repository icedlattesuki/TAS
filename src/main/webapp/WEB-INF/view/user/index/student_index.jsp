<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="course" type="com.se.global.domain.Course"--%>
<%--@elvariable id="courseList" type="java.util.ArrayList<Course>"--%>
<%--@elvariable id="notice" type="com.se.notice.domain.Notice"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浙江大学教学辅助系统</title>
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
                <a href="/index" class="brand-logo">TAS</a>
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
                                    ${notice.totalNumber}
                                </small>
                            </i>
                        </a>
                    </li>
                    <li>
                        <%--<i class="avatar-status"><img src="${user.imageLocation}">--%>
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
        <c:forEach var="course" items="${courseList}" varStatus="status">
            <c:if test="${status.index % 3 == 0}">
                <div class="row">
            </c:if>
            <div class="col s12 m4 l4">
                <div class="card">
                    <div class="card-content black-text">
                        <span class="card-title">${course.name}</span>
                        <p>
                            ${course.courseKey.semester += " " += course.courseKey.time}
                        </p>
                        <p>
                            ${course.courseKey.place}
                        </p>
                        <p>
                            ${course.credit}学分
                        </p>
                        <p>
                            ${course.college}
                        </p>
                    </div>
                    <div class="card-action">
                        <a href="/course/index?courseIndex=${status.index}" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">进入管理</a>
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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
</body>
</html>
