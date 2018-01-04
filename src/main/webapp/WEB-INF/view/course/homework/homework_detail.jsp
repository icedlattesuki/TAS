<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s0 m1 l1"></div>
            <div class="col s12 m10 l8">
                <div class="section"></div>
                <h4>作业详情</h4>
                <h5>Homework Detail</h5>
                <div class="divider"></div>
                <br>
                <div class="card section">
                    <div class="card-content black-text">
                        <span class="card-title">作业名称</span>
                        <div><p>作业内容：${homework.content}</p>
                            <p>截止日期：${homework.ddl_date}</p>
                            <p>满分：${homework.score}</p>
                            <div>
                                作业要求:
                                <a href="/course/homework/download?file_id=${attachment.file_id}">
                                        ${attachment.name}
                                </a>
                            </div>
                            <br>
                        </div>
                        <div class="card-action">
                            <c:if test="${userType == 2}">
                                <a href="/course/${homework.course_id}/homework/${homework.id}/upload_list/" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">
                                    <i class="material-icons right">
                                        format_list_numbered
                                    </i>
                                    提交列表
                                </a>
                                <br>
                                <br>
                                <a href="/course/${homework.course_id}/homework/${homework.id}/to-update/" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">
                                    <i class="material-icons right">
                                        edit
                                    </i>
                                    编辑作业
                                </a>
                            </c:if>
                            <c:if test="${userType == 1}">
                            <div class="row">
                                <form id="uploadForm" class="col s12" action="/course/${homework.course_id}/homework/${homework.id}/upload"
                                      method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col s0">
                                            <div class="file-field input-field">
                                                <div class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">
                                                    <span>选择文件...</span>
                                                    <input type="file" name="file">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col s6">
                                            <button class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow"
                                                    name="submit" type="submit" >
                                                提交
                                                <i class="material-icons right">file_upload</i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <a href="/course/homework/download?file_id=${uploadHomework.upload_homework_file}">
                                    ${uploadHomework.name}
                            </a>
                        </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col s0 m1 l1"></div>
        </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/student_course_tmpl.jsp"/>