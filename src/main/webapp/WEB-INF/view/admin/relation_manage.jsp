<%--@elvariable id="students" type="java.util.ArrayList<Student>"--%>
<%--@elvariable id="teachers" type="java.util.ArrayList<Teacher>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s1 m1 l1"></div>
            <div class="col s10 m9 l9">
                <div class="section">
                    <p style="color: red">${info}</p>
                </div>
                <div class="row">
                    <div class="col s7">
                        <h5>学生-课程</h5>
                        <div class="divider"></div>
                        <form class="row" method="post" action="/admin/relation/student/add/single">
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="id" type="text" class="validate" name="id" required>
                                    <label for="id">学号</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="name" type="text" class="validate" name="code" required>
                                    <label for="name">课程代码</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="college" type="text" class="validate" name="semester" required>
                                    <label for="college">学期</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="major" type="text" class="validate" name="time" required>
                                    <label for="major">时间</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="grade" type="text" class="validate" name="place" required>
                                    <label for="grade">地点</label>
                                </div>
                            </div>
                            <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>导入</button>
                        </form>
                    </div>
                    <div class="col s1"></div>
                    <div class="col s4">
                        <h5>批量导入</h5>
                        <div class="divider"></div>
                        <form class="row" action="/admin/relation/student/add/multi" enctype="multipart/form-data" method="post">
                            <div class="file-field input-field">
                                <div class="btn">
                                    <span>上传文件</span>
                                    <input type="file" name="file" required>
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                            <br/>
                            <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>导入</button>
                        </form>
                    </div>
                </div>

                <div class="section"></div>

                <div class="row">
                    <div class="col s7">
                        <h5>教师-课程</h5>
                        <div class="divider"></div>
                        <form class="row" method="post" action="/admin/relation/teacher/add/single">
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="id1" type="text" class="validate" name="id" required>
                                    <label for="id1">工号</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="name1" type="text" class="validate" name="code" required>
                                    <label for="name1">课程代码</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="college1" type="text" class="validate" name="semester" required>
                                    <label for="college1">学期</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="major1" type="text" class="validate" name="time" required>
                                    <label for="major1">时间</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="grade1" type="text" class="validate" name="place" required>
                                    <label for="grade1">地点</label>
                                </div>
                            </div>
                            <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>导入</button>
                        </form>
                    </div>
                    <div class="col s1"></div>
                    <div class="col s4">
                        <h5>批量导入</h5>
                        <div class="divider"></div>
                        <form class="row" action="/admin/relation/teacher/add/multi" enctype="multipart/form-data" method="post">
                            <div class="file-field input-field">
                                <div class="btn">
                                    <span>上传文件</span>
                                    <input type="file" name="file" required>
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                            <br/>
                            <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>导入</button>
                        </form>
                    </div>
                </div>

                    <div class="section"></div>

                    <div class="row">
                        <h5>搜索课程</h5>
                        <div class="divider"></div>
                        <form class="row" method="post" action="/admin/relation/search">
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="name2" type="text" class="validate" name="code" required>
                                    <label for="name2">课程代码</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="college2" type="text" class="validate" name="semester" required>
                                    <label for="college2">学期</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="major2" type="text" class="validate" name="time" required>
                                    <label for="major2">时间</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="grade2" type="text" class="validate" name="place" required>
                                    <label for="grade2">地点</label>
                                </div>
                            </div>
                            <button class="btn waves-effect waves-light" type="submit"><i class="material-icons right">send</i>搜索</button>
                        </form>
                        <table class="row striped">
                            <c:if test="${students.size() > 0 || teachers.size() > 0}">
                                <thead>
                                <tr>
                                    <th>学号</th>
                                    <th>名称</th>
                                    <th>学院</th>
                                    <th>专业</th>
                                    <th>职称</th>
                                    <th>邮箱</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="teacher" items="${teachers}">
                                    <tr>
                                        <th>${teacher.id}</th>
                                        <th>${teacher.name}</th>
                                        <th>${teacher.college}</th>
                                        <th></th>
                                        <th>${teacher.title}</th>
                                        <th>${teacher.email}</th>
                                        <th><a href="/admin/relation/teacher/remove?id=${teacher.id}&course_id=${courseId}" class="waves-effect waves-light btn gradient-45deg-red-pink box-shadow">删除</a></th>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="student" items="${students}">
                                    <th>${student.id}</th>
                                    <th>${student.name}</th>
                                    <th>${student.college}</th>
                                    <th>${student.major}</th>
                                    <th></th>
                                    <th>${student.email}</th>
                                    <th><a href="/admin/relation/teacher/remove?id=${student.id}&course_id=${courseId}" class="waves-effect waves-light btn gradient-45deg-red-pink box-shadow">删除</a></th>
                                </c:forEach>
                                </tbody>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col s1 m2 l2"></div>
        </div>
    </div>
</tmpl:overwrite>
<jsp:include page="../template/admin_tmpl.jsp"/>