<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="script">
    <script>
        function validateForm()
        {
            var oldp = document.forms["passwdform"]["oldPassword"].value;
            var newp = document.forms["passwdform"]["newPassword"].value;
            var Rnewp = document.forms["passwdform"]["newPassword1"].value;

            if ( oldp == null || oldp == ""){
                Materialize.toast('请输入原密码', 4000)
                return false;
            }
            else if ( newp == null || newp == ""){
                Materialize.toast('请输入新密码', 4000)
                return false;
            }
            else if( oldp == newp)
            {
                Materialize.toast('新密码与旧密码一致', 4000)
                return false;
            }
            else if ( Rnewp == null || Rnewp == ""){
                Materialize.toast('请重新输入新密码', 4000)
                return false;
            }
            else if( Rnewp != newp )
            {
                Materialize.toast('两次新密码不一致', 4000)
                return false;
            }
            else
            {
                return true;
            }
        }
    </script>
</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
    <div class="row">
        <div class="col s1 m1 l1"></div>
        <div class="col s10 m9 l9">
            <div class="section"></div>
            <h4 align="left">修改密码</h4>
            <h5 class="left">Modify Password</h5>
            <div class="divider row"></div>
            <form class="row" name="passwdform" method="post"
                  action="/user/password-modify/modify" onsubmit="return validateForm()">
                <div class="row">
                    <div class="input-field col s12 m9 l9">
                        <i class="material-icons prefix">lock</i>
                        <input id="password1" name="oldPassword" type="password" class="validate">
                        <label for="password1">原密码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 m9 l9">
                        <i class="material-icons prefix">lock_outline</i>
                        <input id="password2" name="newPassword" type="password" class="validate">
                        <label for="password2">新密码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 m9 l9">
                        <i class="material-icons prefix">lock_open</i>
                        <input id="password3" name="newPassword1" type="password" class="validate">
                        <label for="password3">确认新密码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12" align="left">
                        <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>确定</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col s1 m2 l2"></div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/info_center_tmpl.jsp" />