<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
<div class="row">
    <div class="col s0 m1 l1"></div>
    <div class="col s12 m9 l9">
        <div class="card section">
            <div class="card-content black-text">
                <span class="card-title">${announcement.title}</span>
                <p>${announcement.content}</p>
                <p>${announcement.date}</p>
            </div>
            <div class="card-action">
                <a href="/course/${courseId}/announcement/list" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">
                    <i class="material-icons right">
                        more_horiz
                    </i>
                    MORE
                </a>
            </div>
        </div>
    </div>
    <div class="col s0 m2 l2"></div>
</div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/student_course_tmpl.jsp"/>