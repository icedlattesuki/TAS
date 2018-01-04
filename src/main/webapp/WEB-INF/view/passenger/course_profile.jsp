<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    </div>
                </div>
            </div>
            <div class="col s1 m2 l2"></div>
        </div>
        <c:forEach var="teacher" items="${teachers}">
            <div class="row">
                <div class="col s1 m1 l1"></div>
                <div class="col s10 m9 l9">
                    <div class="card section">
                        <div class="card-content black-text">
                            <span class="card-title">${teacher.name}</span>
                            <div class="divider"></div>
                            <div class="row"></div>
                            <p>${teacher.title}</p>
                            <br/>
                            <p>${teacher.college}</p>
                            <br/>
                            <p>${teacher.email}</p>
                            <br/>
                            <p>${teacher.profile}</p>
                        </div>
                    </div>
                </div>
                <div class="col s1 m2 l2"></div>
            </div>
        </c:forEach>
    </div>
</tmpl:overwrite>
<jsp:include page="../template/passenger_course_tmpl.jsp"/>