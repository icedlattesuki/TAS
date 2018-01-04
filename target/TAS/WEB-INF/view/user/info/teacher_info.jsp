<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="com.se.global.domain.Teacher"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<html>
<head>
    <title>User_Student_Info | TAS </title>
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
                        <a href="/notice" class="waves-effect waves-block waves-light" data-activates="notifications-dropdown">
                            <i class="material-icons">
                                notifications_none
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
<div>
    <div>
        <aside>
            <ul class="side-nav fixed leftside-navigation">
                <li>
                    <a href="#">
                        <i class="material-icons left">
                            format_list_bulleted
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        课程信息
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="material-icons left">
                            account_box
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        个人信息
                    </a>
                </li>
                <li>
                    <a href="/user/password-modify">
                        <i class="material-icons left">
                            lock_outline
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        修改密码
                    </a>
                </li>
                <li>
                    <a href="../email/email_bind.html">
                        <i class="material-icons left">
                            email
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        绑定邮箱
                    </a>
                </li>
                <li>
                    <a href="/notice">
                        <i class="material-icons left">
                            notifications
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        消息中心
                    </a>
                </li>
            </ul>
        </aside>
        <div class="row">
            <div class="col s0 m3 13"></div>
            <div class="col s12 m8 18">
                <div>
                    <div class="container page-container">
                        <div>
                            <div>
                                <div id="tab-infomation" class="col s12">
                                    <div class="row">
                                        <div class="col m6">
                                            <div class="row">
                                                <br>
                                                <h4>个人信息</h4>
                                                <h5>Profile</h5>
                                                <div class="divider"></div>
                                            </div>

                                            <ul class="collapsible" data-collapsible="accordion">
                                                <li>
                                                    <div class="collapsible-header active"><i class="material-icons">filter_1</i>姓名 | Name</div>
                                                    <div class="collapsible-body"><span>${user.name}</span></div>
                                                </li>
                                                <li>
                                                    <div class="collapsible-header"><i class="material-icons">filter_2</i>工号 | ID</div>
                                                    <div class="collapsible-body"><span>${user.id}</span></div>
                                                </li>
                                                <li>
                                                    <div class="collapsible-header"><i class="material-icons">filter_3</i>学院 | Dept</div>
                                                    <div class="collapsible-body"><span>${user.college}</span></div>
                                                </li>
                                                <li>
                                                    <div class="collapsible-header"><i class="material-icons">filter_4</i>职称 | Position</div>
                                                    <div class="collapsible-body"><span>${user.title}</span></div>
                                                </li>
                                                <li>
                                                    <div class="collapsible-header"><i class="material-icons">filter_5</i>邮箱 | Email</div>
                                                    <div class="collapsible-body"><span>${user.email}</span></div>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col m6 avatars">
                                            <div class="row">
                                                <br>
                                                <h4>个性化设置</h4>
                                                <h5>Avatar</h5>
                                                <div class="divider"></div>
                                            </div>
                                            <div class="row">
                                                <div class="col m8">
                                                    <ul class="collapsible" data-collapsible="accordion">
                                                        <li>
                                                            <div class="collapsible-header active"><i class="material-icons">filter_drama</i>个性签名 | Sign</div>
                                                            <div class="collapsible-body"><span>${user.signature}</span></div>
                                                        </li>
                                                    </ul>
                                                    <a href="/user/info/edit">
                                                        <div class="col m8">
                                                            <button class="waves-effect btn waves-light" type="submit">编辑资料</button>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col m4">
                                                    <div class="row">
                                                        <div class="card z-depth-2">
                                                            <div class="card-image">
                                                                <img src="${user.imageLocation}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
</body>
</html>
