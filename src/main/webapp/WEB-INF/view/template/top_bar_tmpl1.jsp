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
    <tmpl:block name="content1"/>
</tmpl:overwrite>
<jsp:include page="base.jsp"/>