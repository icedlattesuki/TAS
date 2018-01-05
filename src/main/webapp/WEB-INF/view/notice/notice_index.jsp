<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
    <div>
        <div>
            <aside>
                <ul class="side-nav fixed leftside-navigation">
                    <c:forEach var="course" items="${courses}" varStatus="status">
                        <li>
                            <a href="/notice/${course.id}">
                                <i class="material-icons left">
                                    notifications
                                    <small class="notification-badge">
                                            ${noticeCourseNum.get(status.index)}
                                    </small>
                                </i>
                                    ${course.name}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </aside>
        </div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../template/notice_center_tmpl.jsp"/>