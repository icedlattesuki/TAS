<%--@elvariable id="materials" type="java.util.ArrayList<com.se.courses.resource.material.domain.Material>"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
        <div class="container">
            <div class="row">
                <div class="col s1 m1 l1"></div>
                <div class="col s10 m9 l9">
                    <div class="row">
                        <h4>资料列表</h4>
                        <h5>Material List</h5>
                        <div class="divider row"></div>
                        <div class="input-field col s12">
                            <ul class="collection with-header">
                                <c:forEach var="material" items="${materials}">
                                <li class="collection-item">
                                    <div> 
                                        <a href="/course/${courseId}/resource/material/download?file_id=${material.id}">${material.name}</a> ${material.size1} ${material.date}
                                    </div>
                                </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col s1 m2 l2"></div>
            </div>
        </div>
</tmpl:overwrite>
<jsp:include page="../../../template/student_course_tmpl.jsp"/>
