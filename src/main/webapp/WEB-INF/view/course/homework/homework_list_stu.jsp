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
                <div class="row">
                    <div class="input-field col s12">
                        <ul class="collection with-header">
                            <li class="collection-header">
                                <h4>作业列表</h4>
                                <h5>Homework List</h5>
                            </li>
                            <c:forEach var="homework" items="${homeworkList}">
                                <li class="collection-item">
                                    <div>
                                        <a href="/course/${course_id}/homework/${homework.id}">${homework.title}</a>
                                        <c:if test="${userType == 2}">
                                            <a href="/course/${course_id}/homework/${homework.id}/delete"
                                               class="secondary-content materialize-red-text">
                                                <i class="material-icons">
                                                    delete_forever
                                                </i>
                                            </a>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col s0 m1 l1"></div>
        </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/student_course_tmpl.jsp"/>