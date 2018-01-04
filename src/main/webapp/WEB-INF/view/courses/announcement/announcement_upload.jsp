<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="script">
    <script>
        function validateAnnounce()
        {
            var ann_title = document.forms["announceform"]["title"].value;
            var ann_con = document.forms["announceform"]["content"].value;

            if ( ann_title == null || ann_title == ""){
                Materialize.toast('请输入公告标题', 4000)
                return false;
            }
            else if ( ann_con == null || ann_con == ""){
                Materialize.toast('请输入公告内容', 4000)
                return false;
            }
            else if( ann_title.length > 20)
            {
                Materialize.toast('公告标题过长', 4000)
                return false;
            }
            else if ( ann_con.length > 140)
            {
                Materialize.toast('公告内容过长', 4000)
                return false;
            }
            else
            {
                return true;
            }

        }
    </script>
</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
    <div class="row">
        <div class="col s2 m1 l1"></div>
        <div class="col s8 m9 l9">
            <div class="section"></div>
            <h4 align="left">发布公告</h4>
            <h5 align="left">Announce</h5>
            <div class="divider row"></div>
            <form class="row" name="announceform" method="post"
                  action="/course/${courseId}/announcement/upload" onsubmit="return validateAnnounce()">
                <div class="row">
                    <div class="input-field col s9">
                        <i class="material-icons prefix">subtitles</i>
                        <input id="announce_title" name="title" type="text" class="validate" data-length="15">
                        <label for="announce_title">公告标题</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s9">
                        <i class="material-icons prefix">content_copy</i>
                        <textarea id="announce_content" name="content" class="materialize-textarea" data-length="140"></textarea>
                        <label for="announce_content">公告内容</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12" align="left">
                        <button class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow" type="submit">
                            <i class="material-icons right">send</i>
                            发布
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col s2 m2 l2"></div>
    </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>