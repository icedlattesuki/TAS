<%--
  Created by IntelliJ IDEA.
  User: yusen
  Date: 2017/12/24
  Time: 下午2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <title>浙江大学教学辅助系统</title>
    <link rel="stylesheet" type="text/css" href="/css/user/index/user_index.css">

    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.css" media="screen,projection"/>

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
                        学生管理
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="material-icons left">
                            folder_open
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        教师管理
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="material-icons left">
                            video_library
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        课程管理
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="material-icons left">
                            comment
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        关联管理
                    </a>
                </li>
            </ul>
        </aside>
        <div class="row">
            <div class="col s0 m2 l2"></div>
            <div class="col s12 m9 l9">
                <div class="section">

                </div>
                <h5 style="color: red;">${info}</h5>
                <h5>单个导入</h5>
                <div class="divider"></div>
                <form class="col s12" method="post" action="/admin/add/student/single">
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="id" type="text" class="validate" name="id">
                            <label for="id">学号</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="name" type="text" class="validate" name="name">
                            <label for="name">姓名</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="college" type="text" class="validate" name="college">
                            <label for="college">学院</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="major" type="text" class="validate" name="major">
                            <label for="major">专业</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="grade" type="text" class="validate" name="grade">
                            <label for="grade">年级</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="class_number" type="text" class="validate" name="class_number">
                            <label for="class_number">班级</label>
                        </div>
                    </div>
                    <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>导入</button>
                </form>
                <div class="section">

                </div>
                <h5>批量导入</h5>
                <div class="divider"></div>
                <div class="section">

                </div>
                <h5>搜索学生</h5>
                <div class="divider"></div>
            </div>
            <div class="col s0 m1 l1"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
</body>
</html>
</html>