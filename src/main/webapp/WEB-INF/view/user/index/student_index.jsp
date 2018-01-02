<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="course" type="com.se.global.domain.Course"--%>
<%--@elvariable id="courses" type="java.util.ArrayList<Course>"--%>
<%--@elvariable id="noticeTotalNum" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content1">
    <div class="container">
    <div class="row">
        <div class="col s12 m4 l4">

        </div>
    </div>
    <div class="row">
        <div class="col s0 m2 l2">

        </div>
        <div class="col s12 m8 l8">
            <div class="section">
                <h5>Course</h5>
            </div>
            <div class="divider">

            </div>
            <div class="section">

            </div>
            <c:forEach var="course" items="${courses}" varStatus="status">
                <c:if test="${status.index % 3 == 0}">
                    <div class="row">
                </c:if>
                <div class="col s12 m12 l4">
                    <div class="card">
                        <div class="card-content black-text">
                            <span class="card-title">${course.name}</span>
                            <p>
                                    ${course.semester += " " += course.time}
                            </p>
                            <p>
                                    ${course.place}
                            </p>
                            <p>
                                    ${course.credit}学分
                            </p>
                            <p>
                                    ${course.college}
                            </p>
                        </div>
                        <div class="card-action">
                            <a href="/course/${course.id}" class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow">进入</a>
                        </div>
                    </div>
                </div>
                <c:if test="${(status.index + 1) % 3 == 0}">
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="col s0 m2 l2">
        </div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/top_bar_tmpl.jsp"/>