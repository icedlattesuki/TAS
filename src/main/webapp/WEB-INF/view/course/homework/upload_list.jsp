<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s0 m1 l1"></div>
            <div class="col s12 m12 l11">
                <div class="section"></div>
                <div class="row">
                    <div class="col s12">
                        <ul class="collection with-header">
                            <li class="collection-header">
                                <h4>提交列表</h4>
                                <h5>Submit List</h5>
                            </li>
                            <c:forEach var="uploadHomework" items="${uploadHomeworkList}">
                                <li class="collection-item">
                                    <div>
                                            ${uploadHomework.studentId},${uploadHomework.studentName}
                                        <c:if test="${uploadHomework.uploadFileId != -1}">
                                            <a href="/course/homework/download?file_id=${uploadHomework.uploadFileId}" class="secondary-content">${uploadHomework.uploadFileName}</a>
                                        </c:if>
                                        <c:if test="${uploadHomework.uploadFileId == -1}">
                                            <div class="secondary-content">
                                                <span class="red-text text-read">还未提交</span>
                                            </div>
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