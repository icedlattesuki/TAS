<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%--@elvariable id="user" type="com.se.global.domain.Student"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<tmpl:overwrite name="body">
    <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav>
                <div class="nav-wrapper">
                    <a href="/index" class="brand-logo">TAS</a>
                    <div class="header-search-wrapper hide-on-med-and-down sideNav-lock">
                        <form class="search-form" method="post" action="/index/s">
                            <i class="material-icons">search</i>
                            <input type="text" name="Keyword" class="header-search-input z-depth-2" placeholder="Explore">
                        </form>
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
    <tmpl:block name="content1"/>
</tmpl:overwrite>
<jsp:include page="base.jsp"/>