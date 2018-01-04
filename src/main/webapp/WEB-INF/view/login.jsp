<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浙江大学教学辅助系统 | TAS</title>

    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.css"  media="screen,projection"/>

    <link type="text/css" rel="stylesheet" href="/css/style.css" />

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script>
        function validateLoginForm()
        {
            var id = document.forms["loginform"]["id"].value;
            var pass = document.forms["loginform"]["password"].value;
            
            if ( id == null || id == ""){
                Materialize.toast('请输入学号或工号', 4000)
                return false;
            }
            else if ( pass == null || pass == ""){
                Materialize.toast('请输入密码', 4000)
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
<div class="row">
    <div class="col s0 m4 l4"></div>
    <div class="col s0 m4 l4">
        <div class="section"></div>
        <h3 align="center">Welcome to TAS</h3>
        <!--
        <p align="center" style="color: red">${error}</p>-->
        <form class="col s12" name="loginform" method="post" action="/login/check" onsubmit="return validateLoginForm()">
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="id" name="id" type="text" class="validate">
                    <label for="id">学号或工号</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">lock</i>
                    <input id="password" name="password" type="password" class="validate">
                    <label for="password">密码</label>
                </div>
            </div>
            <div class="row">
                <div class="col s12" align="center">
                    <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>登录</button>
                </div>
            </div>
            <div class="row">
                <div class="col s12" align="center">
                    <i class="material-icons prefix">train</i>
                    <a href="/index">游客登录</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <i class="material-icons prefix">lock_outline</i>
                    <a href="/user/password-reset">重置密码</a>
                </div>
            </div>
        </form>
    </div>
    <div class="col s0 m4 l4"></div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
</body>
</html>
