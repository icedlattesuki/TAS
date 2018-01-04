<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="script">
    <script>
        function validateEmail()
        {
            var semail = document.forms["emailform"]["email"].value;

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
</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
    <div class="row">
        <div class="col s1 m1 11"></div>
        <div class="col s10 l9 l9">
            <div>
                <div class="container page-container">
                    <div>
                        <div>
                            <div id="tab-infomation" class="col s12">
                                <div class="row">
                                    <div class="col m12">
                                        <div class="row">
                                            <br>
                                            <h4>绑定邮箱</h4>
                                            <h5>Bind Email</h5>
                                            <div class="divider"></div>
                                        </div>
                                        <form class="row" name="emailform" method="post"
                                              action="/user/email/to-bind" onsubmit="return validateEmail()">
                                            <div class="row">
                                                <div class="input-field col s8">
                                                    <!--<i class="material-icons prefix">email</i>-->
                                                    <input id="Email" name="email" type="email" class="validate">
                                                    <label for="Email" data-error="邮箱格式有误" data-success="邮箱格式正确">邮箱 | Email</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col s12">
                                                    <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>绑定</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s12 avatars">
                                        <div class="row">
                                            <br>
                                            <h4>解除绑定</h4>
                                            <h5>Unbind</h5>
                                            <div class="divider row"></div>
                                        </div>
                                        <div class="row">
                                            <form class="col s12" name="emailform" method="post"
                                                  action="/user/email/to-unbind" onsubmit="return validateForm()">
                                                <div class="row">
                                                    <div class="col s12">
                                                        <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>解绑</button>
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
        <div class="col s1 m2 12"></div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/info_center_tmpl.jsp" />