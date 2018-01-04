<%--
  Created by IntelliJ IDEA.
  User: linsp
  Date: 2017/12/16
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Assign Homework | TAS</title>
    <link type="text/css" rel="stylesheet" href="/css/materialize.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script>
        function validateHomework()
        {
            var hwtitle = document.forms["homeworkform"]["title"].value;
            var hwddl = document.forms["homeworkform"]["ddl"].value;
            var hwscore = document.forms["homeworkform"]["score"].value;
            var hwcontent = document.forms["homeworkform"]["content"].value;

            if ( hwtitle == null || hwtitle == ""){
                Materialize.toast('请输入作业名称', 4000)
                return false;
            }
            else if ( hwddl == null || hwddl == ""){
                Materialize.toast('请选择作业截止日期', 4000)
                return false;
            }
            else if ( hwscore == null || hwscore == ""){
                Materialize.toast('请输入作业分数', 4000)
                return false;
            }
            else if ( hwcontent == null || hwcontent == ""){
                Materialize.toast('请输入作业内容', 4000)
                return false;
            }
            else if( hwtitle.length > 30 ){
                Materialize.toast('作业名称过长', 4000)
                return false;
            }
            else if( hwtitle.length < 8 ){
                Materialize.toast('作业名称过短', 4000)
                return false;
            }
            else if( hwscore < 1 || hwscore > 100 ){
                Materialize.toast('作业分数应在0-100分之间', 4000)
                return false;
            }
            else if( hwcontent.length > 140 ){
                Materialize.toast('作业内容过长', 4000)
                return false;
            }
            else if( hwcontent.length < 10 ){
                Materialize.toast('作业内容过短', 4000)
                return false;
            }
            else
            {
                return true;
            }

        }
    </script>
</head>
<body>
<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>
<header id="header" class="page-topbar">
    <!-- start header nav-->
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper">
                <ul class="left">
                    <li>
                        <h1 class="logo-wrapper">
                            <a href="/index" class="brand-logo darken-1">
                                <!-- <img src="../../../img/tas_logo.png" alt="materialize logo"> -->
                                <span class="logo-text">
                                                TAS
                                        </span>
                            </a>
                        </h1>
                    </li>
                </ul>
                <div class="header-search-wrapper hide-on-med-and-down sideNav-lock">
                    <i class="material-icons">search</i>
                    <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore">
                </div>
                <ul class="right hide-on-med-and-down">
                    <li>
                        <a href="../../notice/notice_course.html">
                            <i class="material-icons">
                                notifications
                                <small class="notification-badge">
                                    0
                                </small>
                            </i>
                        </a>
                    </li>
                    <li>
                        <a href="../../user/info/teacher_info.html">
                            <i class="material-icons">
                                account_circle
                            </i>
                        </a>
                    </li>
                    <li>
                        <a href="../../login.html">
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
<div id="main">
    <div class="wrapper">
        <aside id="left-sidebar-nav">
            <ul id="slide-out" class="side-nav fixed leftside-navigation">
                <li>
                    <a href="../index/teacher_index.html" >
                        <i class="material-icons left">
                            announcement
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        课程公告
                    </a>
                </li>
                <li>
                    <a href="../introduction/teacher_course_intro.html" >
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
                    <a href="../resource/material/material_manage.html">
                        <i class="material-icons left">
                            folder_open
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        教学资料
                    </a>
                </li>
                <li>
                    <a href="../test/student_test.html">
                        <i class="material-icons left">
                            edit
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        在线测试
                    </a>
                </li>
                <li>
                    <a href="./homework_list.html">
                        <i class="material-icons left">
                            assignment
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        实验作业
                    </a>
                </li>
                <!-- <li>
                    <a href="../group/group_authenticate.html">
                        <i class="material-icons left">
                            group
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        团队认证审核
                    </a>
                </li> -->
                <li>
                    <a href="../resource/video/video_teacher.html">
                        <i class="material-icons left">
                            video_library
                        </i>
                        <i class="material-icons right">
                            chevron_right
                        </i>
                        教学视频
                    </a>
                </li>
                <li>
                    <a href="../comment/comment.html">
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
            <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating waves-effect waves-light hide-on-large-only">
                <i class="material-icons">menu</i>
            </a>
        </aside>
        <div class="container">
            <div class="row">
                <div class="col s0 m1 l1"></div>
                <div class="col s12 m10 l8">
                    <div class="section"></div>
                    <h4>布置作业</h4>
                    <h5>Assign Homework</h5>
                    <div class="divider"></div>
                    <form class="col s12" id="homeworkForm" name="homeworkform" method="post"
                          action="/course/${course_id}/homework/assign" enctype="multipart/form-data" onsubmit="return validateHomework()">
                        <div class="section"></div>
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">subtitles</i>
                                <input id="hw_title" name="title" type="text" class="validate" data-length="30">
                                <label for="hw_title">作业名称</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <i class="material-icons prefix">date_range</i>
                                <input id="hw_ddl" name="ddl" type="text" class="datepicker">
                                <label for="hw_ddl">截止日期</label>
                            </div>
                            <div class="input-field col s6">
                                <i class="material-icons prefix">grade</i>
                                <input id="hw_score" name="score" type="number" class="validate">
                                <label for="hw_score">作业分数</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">content_copy</i>
                                <textarea id="hw_content" name="content" class="materialize-textarea" data-length="140"></textarea>
                                <label for="hw_content">作业内容</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="file-field input-field col s12">
                                <div class="btn">
                                    <span>附件上传</span>
                                    <input type="file" name="file">
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12" align="center">
                                <button class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow" type="submit">
                                    <i class="material-icons right">send</i>
                                    确定
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col s0 m1 l1"></div>
            </div>
        </div>
    </div>
</div>
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
</footer>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
<script type="text/javascript" src="/js/init.js"></script>
<script type="text/javascript" src="/js/plugins.js"></script>
</body>
</html>
