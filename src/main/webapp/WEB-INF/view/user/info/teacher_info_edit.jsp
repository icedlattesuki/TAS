<%--@elvariable id="user" type="com.se.global.domain.Student"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="script">
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
</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
    <div class="row">
        <div class="col s0 m1 l1"></div>
        <div class="col s12 m10 l10">
            <div>
                <div class="container page-container">
                    <div>
                        <div>
                            <div id="tab-infomation" class="col s12">
                                <div class="row">
                                    <div class="col s12 m6 l6">
                                        <div class="row">
                                            <br>
                                            <h4>个人信息</h4>
                                            <h5>Profile</h5>
                                            <div class="divider"></div>
                                        </div>
                                        <div class="row"></div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="info-username" disabled value="${user.name}" type="text"
                                                       class="black-text">
                                                <label for="info-username" class="teal-text text-darken-3" active>
                                                    姓名 | Name
                                                </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="info-userID" disabled value="${user.id}" type="text"
                                                       class="black-text">
                                                <label for="info-userID" class="teal-text text-darken-3" active>
                                                    学号 | ID
                                                </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="info-department" disabled value="${user.college}" type="text"
                                                       class="black-text">
                                                <label for="info-department" class="teal-text text-darken-3" active>
                                                    学院 | Dept
                                                </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="info-title" disabled value="${user.type}" type="text"
                                                       class="black-text">
                                                <label for="info-department" class="teal-text text-darken-3" active>
                                                    职称 | Title
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s12 m6 l6 avatars">
                                        <div class="row">
                                            <br>
                                            <h4>个性化设置</h4>
                                            <h5>Avatar</h5>
                                            <div class="divider"></div>
                                        </div>
                                        <form class="row" action="/user/info/update" method="post" enctype="multipart/form-data" onsubmit="return validateSign()">
                                            <div class="col s8">
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
                                            <div class="col s4">
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
        <div class="col s0 m1 l1"></div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/info_center_tmpl.jsp" />
