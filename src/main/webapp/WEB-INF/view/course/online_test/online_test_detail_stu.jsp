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
                <h3>${onlineTest.title}</h3>
                <div class="divider"></div>
                <form action="#">
                    <div class="row">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">date_range</i>
                            <input id="hw_ddl" name="hw_ddl" type="text" class="datepicker" disabled="disabled">
                            <label for="hw_ddl">${onlineTest.ddl_date}</label>
                        </div>
                    </div>
                    <div class="section">
                        <c:forEach var="choiceQuestion" items="${choiceQuestions}" varStatus="status">
                            <p>第${status.index+1}题.${choiceQuestion.title}</p>
                            <p>${choiceQuestion.score}分</p>
                            <form action="#">
                                <p>
                                    <input name="group1" type="radio" id="option1" />
                                    <label for="option1">${choiceQuestion.choice_a}</label>
                                </p>
                                <p>
                                    <input name="group1" type="radio" id="option2" />
                                    <label for="option2">${choiceQuestion.choice_b}</label>
                                </p>
                                <p>
                                    <input name="group1" type="radio" id="option3" />
                                    <label for="option3">${choiceQuestion.choice_c}</label>
                                </p>
                                <p>
                                    <input name="group1" type="radio" id="option4" />
                                    <label for="option4">${choiceQuestion.choice_d}</label>
                                </p>
                            </form>
                            <button class="btn waves-effect waves-light" type="submit" name="action" style="float:right;">
                                提交答案
                            </button>
                            <br>
                        </c:forEach>
                    </div>
                    <br><br><div class="divider"></div>
                    <div class="section">
                        <c:forEach var="fillQuestion" items="${fillQuestions}" varStatus="status">
                            <p>第${status.index+1}题.${fillQuestion.title}</p>
                            <p>${fillQuestion.score}分</p>
                            <div class="row">
                                <div class="input-field col s12">
                                    <textarea id="question_content" class="materialize-textarea"></textarea>
                                    <label for="question_content">简答题回答</label>
                                </div>
                            </div>
                            <button class="btn waves-effect waves-light" type="submit" name="action" style="float:right;">
                                提交答案
                            </button>
                            <br>
                        </c:forEach>
                    </div>
                </form>
                <div class="col s0 m1 l1"></div>
            </div>
        </div>
    </div>
</tmpl:overwrite>..
<jsp:include page="../../template/student_course_tmpl.jsp"/>