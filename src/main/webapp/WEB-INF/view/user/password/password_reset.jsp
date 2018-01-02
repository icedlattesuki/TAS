<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld"%>
<tmpl:overwrite name="script">
    <script>
        function validateForm()
        {
            var fid = document.forms["resetform"]["id"].value;
            var femail = document.forms["resetform"]["email"].value;

            if ( fid == null || fid == ""){
                Materialize.toast('请输入学号或工号', 4000)
                return false;
            }
            else if ( femail == null || femail == ""){
                Materialize.toast('请输入邮箱', 4000)
                return false;
            }
            else
            {
                return true;
            }

        }
    </script>
</tmpl:overwrite>
<tmpl:overwrite name="body">
    <div class="row">
        <div class="col s0 m4 l4"></div>
        <div class="col s0 m4 l4">
            <div class="section"></div>
            <h3 align="center">重置密码</h3>
            <div class="row">
                <div class="col s12">
                    <div class="card teal lighten-2">
                        <div class="card-content white-text">
                            <span class="card-title">提示</span>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请填写以下信息，点击确定，
                                系统将发送邮件到您的邮箱，点击邮件中的链接重置密码。</p>
                        </div>
                    </div>
                </div>
            </div>
            <form class="col s12" name="resetform" method="post" action="/user/password-reset/to-reset" onsubmit="return validateForm()">
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="id" name="id" type="text" class="validate">
                        <label for="id">学工号</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">email</i>
                        <input id="email" name="email" type="email" class="validate">
                        <label for="email" data-error="邮箱格式有误" data-success="邮箱格式正确">邮箱</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12" align="center">
                        <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>确定</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col s0 m4 l4"></div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/base.jsp" />