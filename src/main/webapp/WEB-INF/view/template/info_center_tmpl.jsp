<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="content1">
<div id="main">
    <div class="wrapper">
        <aside id="left-sidebar-nav">
            <ul id="slide-out" class="side-nav fixed leftside-navigation">
                <li>
                    <a href="/user/info">
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
                    <a href="/user/email">
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
            <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating waves-effect waves-light hide-on-large-only">
                    <i class="material-icons">menu</i>
            </a>
        </aside>
        <tmpl:block name="content"/>
    </div>
</div>
</tmpl:overwrite>
<jsp:include page="top_bar_tmpl.jsp"/>
