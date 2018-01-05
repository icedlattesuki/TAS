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
                    <div class="section"></div>
                    <h4>上传资料</h4>
                    <h5>Upload Material</h5>
                    <div class="divider row"></div>
                    <form method="post" action="/course/${courseId}/resource/material/upload" enctype="multipart/form-data">
                    <div class="row">
                        <div class="file-field input-field col s12">
                            <div class="btn">
                                <span>选择文件...</span>
                                <input type="file" name="file">
                            </div>
                            <!-- <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div> -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                        <button class="btn waves-effect waves-light" type="submit">
                            <i class="material-icons right">send</i>
                            上传
                        </button>
                        </div>
                    </div>
                    </form>
                    <div class="section"></div>
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
                                    <c:if test="${user.type == 2}">
                                        <a href="/course/${courseId}/resource/material/delete?file_id=${material.id}" class="secondary-content"><i class="material-icons">delete_forever</i></a>
                                    </c:if>
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
<jsp:include page="../../../template/teacher_course_tmpl.jsp"/>
