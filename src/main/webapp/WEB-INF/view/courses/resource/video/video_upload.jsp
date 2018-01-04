<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="script">
    <script>
        function validateVideo()
        {
            var vtitle = document.forms["videoform"]["title"].value;
            var vprofile = document.forms["videoform"]["profile"].value;

            if ( vtitle == null || vtitle == ""){
                Materialize.toast('请输入视频标题', 4000)
                return false;
            }
            else if ( vprofile == null || vprofile == ""){
                Materialize.toast('请输入视频简介', 4000)
                return false;
            }
            else if( vtitle.length > 30 ){
                Materialize.toast('视频标题过长', 4000)
                return false;
            }
            else if( vprofile.length >140 ){
                Materialize.toast('视频简介过长', 4000)
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
    <div class="row">
        <div class="col s1 m1 l1"></div>
        <div class="col s10 m9 l9">
            <div class="section"></div>
            <h4>上传视频</h4>
            <h5>Upload Video</h5>
            <div class="divider"></div>
            <form class="col s12" name="videoform" action="/course/${courseId}/resource/video/upload"
                  method="post" enctype="multipart/form-data" onsubmit="return validateVideo()">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="input1" type="text" name="title" class="validate" data-length="30">
                        <label for="input1">视频标题</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="input2" type="text" name="profile" class="validate" data-length="140">
                        <label for="input2">视频简介</label>
                    </div>
                </div>
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
        </div>
        <div class="col s1 m2 l2"></div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../../template/teacher_course_tmpl.jsp"/>