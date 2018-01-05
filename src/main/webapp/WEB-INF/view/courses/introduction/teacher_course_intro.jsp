<%--@elvariable id="course" type="Course"--%>
<%--@elvariable id="teachers" type=""ArrayList<Teacher>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s01 m1 l1"></div>
            <div class="col s10 m9 l9">
                <div class="card section">
                    <div class="card-content black-text">
                        <span class="card-title">${course.name}</span>
                        <div class="divider"></div>
                        <div class="row"></div>
                        <p>${course.college}</p>
                        <br/>
                        <p>${course.introduction}</p>
                        <br/>
                        <a href="/course/${courseId}/introduction/edit" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">
                            <i class="material-icons right">
                                edit
                            </i>
                            编辑
                        </a>
                    </div>
                </div>
            </div>
            <div class="col s1 m2 l2"></div>
        </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>