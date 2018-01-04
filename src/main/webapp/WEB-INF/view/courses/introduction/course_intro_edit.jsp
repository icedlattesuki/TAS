<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<tmpl:overwrite name="content">
    <div class="row">
        <div class="col s1 m1 l1"></div>
        <div class="col s10 m9 l9">
            <div class="section"></div>
            <h4>编辑课程简介</h4>
            <h5>Edit course intro</h5>
            <div class="divider"></div>
            <form class="col s12" name="videoform" action="/course/${courseId}/introduction/submit"
                  method="post" enctype="multipart/form-data" onsubmit="return validateVideo()">
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">mode_edit</i>
                        <textarea id="icon_prefix2" class="materialize-textarea" name="intro"></textarea>
                        <label for="icon_prefix2">课程简介</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12">
                        <button class="btn waves-effect waves-light" type="submit">
                            <i class="material-icons right">send</i>
                            确认
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col s1 m2 l2"></div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>