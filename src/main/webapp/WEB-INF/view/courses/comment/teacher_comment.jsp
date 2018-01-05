<%--@elvariable id="user" type="com.se.global.domain.User"--%>
<%--@elvariable id="comment" type="com.se.courses.comment.domain.Comment"--%>
<%--@elvariable id="comments" type="java.util.ArrayList<Comment>"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s1 m1 l1"></div>
            <div class="col s11 m10 l10">
                <div class="section"></div>
                <h4>留言板</h4>
                <h5>Comment</h5>
                <div class="divider"></div>
                <div class="row">
                    <div class="col s12">
                        <c:forEach var="comment" items="${comments}" varStatus="status">
                            <div class="section">
                                <div class="row">
                                    <div class="col s2 m1 l1">
                                        <img class="materialboxed" width="100%" height="50" src="${comment.user.imageLocation}">
                                        <p>${comment.user.name}</p>
                                    </div>
                                    <div class="col s9 m10 l10">
                                        <p>${comment.date}</p>
                                        <br/>
                                        ${comment.content}
                                    </div>
                                </div>
                                    <div class="row">
                                        <div class="col s11" align="center">
                                            <a class="waves-effect waves-light btn gradient-45deg-red-pink box-shadow"
                                               style="float:right;" href="/course/${courseId}/comment/remove?comment_id=${comment.commentId}">
                                                <i class="material-icons right">
                                                    delete_forever
                                                </i>
                                                删除留言
                                            </a>
                                        </div>
                                    </div>
                            </div>
                            <div class="divider"></div>
                        </c:forEach>
                    </div>
                </div>
                <form class="row" name="commentform" method="post"
                      action="/course/${courseId}/comment/submit" onsubmit="return validateComment()">
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">mode_edit</i>
                            <textarea id="icon_prefix2" class="materialize-textarea" name="content"></textarea>
                            <label for="icon_prefix2">留言内容</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12" align="center">
                            <button class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow"
                                    name="action" style="float:right;" type="submit">
                                <i class="material-icons right">send</i>
                                留言
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col s0 m1 l1"></div>
        </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>
