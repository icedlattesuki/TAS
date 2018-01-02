<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="content1">
    <div id="main">
        <div class="wrapper">
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation">
                    <li>
                        <a href="/course/${courseId}" >
                            <i class="material-icons left">
                                announcement
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            课程公告
                        </a>
                    </li>
                    <li>
                        <a href="#" >
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
                        <a href="/course/${courseId}/resource/material/to-download">
                            <i class="material-icons left">
                                folder_open
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            资料下载
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="material-icons left">
                                edit
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            在线测试
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="material-icons left">
                                assignment
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            实验作业
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="material-icons left">
                                group
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            团队信息
                        </a>
                    </li>
                    <li>
                        <a href="/course/${courseId}/resource/video/watch">
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
                        <a href="/course/${courseId}/comment">
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
<jsp:include page="top_bar_tmpl.jsp"/>
