<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="content1">
    <div id="main">
        <div class="wrapper">
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation">
                    <li>
                        <a href="/admin/student_manage">
                            <i class="material-icons left">
                                people
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            学生管理
                        </a>
                    </li>
                    <li>
                        <a href="/admin/teacher_manage">
                            <i class="material-icons left">
                                people
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            教师管理
                        </a>
                    </li>
                    <li>
                        <a href="/admin/course_manage">
                            <i class="material-icons left">
                                import_contacts
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            课程管理
                        </a>
                    </li>
                    <li>
                        <a href="/admin/relation_manage">
                            <i class="material-icons left">
                                insert_link
                            </i>
                            <i class="material-icons right">
                                chevron_right
                            </i>
                            关联管理
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
