<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="content1">
    <div id="main">
        <div class="wrapper">
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation">
                    <li>
                        <a href="/passenger/${courseId}" >
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
                        <a href="/passenger/${courseId}/video">
                            <i class="material-icons left">
                                video_library
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            视频观看
                        </a>
                    </li>
                    <li>
                        <a href="/passenger/${courseId}/comment">
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
            <tmpl:block name="content"/>
        </div>
    </div>
</tmpl:overwrite>
<jsp:include page="top_bar_tmpl1.jsp"/>
