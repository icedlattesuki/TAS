<%--@elvariable id="classifiedNotices" type="java.util.ArrayList<ArrayList<Notice>>"--%>
<%--@elvariable id="noticeURLs" type="String[]"--%>
<%--@elvariable id="noticeTypes" type="String[]"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
    <div class="row">
        <div class="col s0 m1 l1"></div>
        <div class="col s12 m9 l9">
            <div>
                <div class="container page-container">
                    <div>
                        <div>
                            <div id="tab-notification" class="col s12">
                                <div class="row">
                                    <br>
                                    <h4>消息中心</h4>
                                    <h5>Notice</h5>
                                    <div class="divider row"></div>
                                </div>
                                <div class="row">
                                    <div class="col s12">
                                        <ul class="collapsible" data-collapsible="expandable">
                                            <c:forEach var="typeNotices" items="${classifiedNotices}" varStatus="status">
                                                <li>
                                                    <div class="collapsible-header active"><i class="material-icons">filter_${status.index + 1}</i>${noticeTypes[status.index]}</div>
                                                    <div class="collapsible-body">
                                                        <c:forEach var="notice" items="${typeNotices}">
                                                            <a href="${"/course/" += notice.courseId += noticeURLs[status.index] += "?notice_id=" += notice.id}">${notice.message}</a>
                                                            <br/>
                                                        </c:forEach>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col s0 m2 l2"></div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../template/notice_center_tmpl.jsp"/>