<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="com.se.global.domain.Student"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<html>
<head>
    <title>User_Student_Info_Edit | TAS </title>
    <link rel="stylesheet" type="text/css" href="/css/user/index/user_index.css">

    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.css"  media="screen,projection"/>

    <link type="text/css" rel="stylesheet" href="/css/style.css">

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script>
        function validateSign()
        {
            var ssign = document.forms["sign"]["stusign"].value;

            if ( ssign == null || ssign == ""){
                Materialize.toast('请输入个性签名', 4000)
                return false;
            }
            else if( ssign.length > 140 )
            {
                Materialize.toast('字数超过限制',4000)
                return false;
            }
            else
            {
                Materialize.toast('签名修改成功！',10000)
                return true;
            }

        }

        function validateEmail()
        {
            var semail = document.forms["email"]["stuemail"].value;

            if ( semail == null || semail == ""){
                Materialize.toast('请输入邮箱', 4000)
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
                                            <div class="row"></div>
                                            <div class="row">
                                                <div class="input-field col m8">
                                                    <input id="info-username" disabled value="${user.name}" type="text"
                                                           class="black-text">
                                                    <label for="info-username" class="teal-text text-darken-3" active>
                                                        姓名 | Name
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col m8">
                                                    <input id="info-userID" disabled value="${user.id}" type="text"
                                                           class="black-text">
                                                    <label for="info-userID" class="teal-text text-darken-3" active>
                                                        学号 | ID
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col m8">
                                                    <input id="info-department" disabled value="${user.college}" type="text"
                                                           class="black-text">
                                                    <label for="info-department" class="teal-text text-darken-3" active>
                                                        学院 | Dept
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col m8">
                                                    <input id="info-title" disabled value="${user.type}" type="text"
                                                           class="black-text">
                                                    <label for="info-department" class="teal-text text-darken-3" active>
                                                        职称 | Title
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col m6 avatars">
                                            <div class="row">
                                                <br>
                                                <h4>个性化设置</h4>
                                                <h5>Avatar</h5>
                                                <div class="divider"></div>
                                            </div>
                                            <form class="row" action="/user/info/update" method="post" enctype="multipart/form-data" onsubmit="return validateSign()">
                                                <div class="col m8">
                                                    <div class="row">
                                                        <div class="col s12" name="sign">
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <textarea id="Sign" name="signature" class="materialize-textarea" data-length="140"></textarea>
                                                                    <label for="Sign">个性签名 | Sign</label>
                                                                </div>
                                                                <div class="col m8">
                                                                    <button class="waves-effect btn waves-light" type="submit">确认修改</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col m4">
                                                    <div class="row">
                                                        <div class="col s2"></div>
                                                        <div class="file-field input-field">
                                                            <div class="btn">
                                                                <span>Upload</span>
                                                                <input type="file" name="image">
                                                            </div>
                                                            <div class="file-path-wrapper">
                                                                <div class="row">
                                                                    <%--<input class="file-path validate" type="text" placeholder="">--%>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
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