<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/6
  Time: 下午10:56
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="announcement" type="com.se.course.announcement.domain.Announcement"--%>
<%--@elvariable id="announcementList" type="java.util.ArrayList<Announcement>"--%>
<%--@elvariable id="courseId" type="int"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

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
                                    ${noticeTotalNum}
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
<div>
    <div>
        <aside>
            <ul class="side-nav fixed leftside-navigation">
                <li>
                    <a href="#" >
                        <i class="material-icons left">
                            format_list_bulleted
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        课程简介
                    </a>
                </li>
                <li>
                    <a href="/course/${courseId}/resource/material/to-download">
                        <i class="material-icons left">
                            folder_open
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        资料下载
                    </a>
                </li>
                <li>
                    <a href="/course/${courseId}/resource/video/watch">
                        <i class="material-icons left">
                            video_library
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        视频观看
                    </a>
                </li>
                <li>
                    <a href="/course/${courseId}/comment">
                        <i class="material-icons left">
                            comment
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        留言板
                    </a>
                </li>
            </ul>
        </aside>
        <div class="row">
            <div class="col s0 m3 l3"></div>
            <div class="col s12 m8 l8">
                <c:forEach var="announcement" items="${announcementList}">
                    <div class="card section">
                        <div class="card-content black-text">
                            <span class="card-title">${announcement.title}</span>
                            <p>${announcement.content}</p>
                            <p>${announcement.date}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="col s0 m1 l1"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
</body>
</html>
