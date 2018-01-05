<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="script">
</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s0 m1 l1"></div>
            <div class="col s12 m10 l10">
                <div class="section"></div>
                <h3>批改主观题</h3>
                <h4>Grade Objective Questions</h4>
                <div class="divider"></div>
                <!-- current questions -->
                <div class="section">
                    <p>1. 这是主观题的题干</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <input disabled value="这是主观题的回答" id="answer" type="text" class="validate">
                        </div>
                    </div>
                    <div class="row">
                        <form action="#">
                            <div class="input-field col s3 offset-s8">
                                <input id="score1" type="text" class="validate">
                                <label for="score1">分数</label>
                            </div>
                            <div class="row">
                                <div class="col s6"></div>
                                <div class="col s5">
                                    <button class="btn waves-effect waves-light" type="submit" name="action" style="float:right;">
                                        提交
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col s0 m1 l1"></div>
            </div>
        </div>
    </div>
</tmpl:overwrite>..
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>