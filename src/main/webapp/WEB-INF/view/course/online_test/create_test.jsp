<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="script">
    <script>
        window.onload = show_choice;
        function show_choice(){
            document.getElementById("add-choice").style.display="inline";
            document.getElementById("add-answer").style.display="none";
        }
        function show_answer(){
            document.getElementById("add-choice").style.display="none";
            document.getElementById("add-answer").style.display="inline";
        }
        var pChoice, pAnswer;

        function addQuestion() {
            var form = document.getElementById("addForm");
            var type = form.elements["question_type"].value;
            if (type == "choice") {
                addChoice(form);
            } else {
                addAnswer(form);
            }
        }
        function addChoice(form) {
            if (pChoice == null) {
                pChoice = document.getElementById("nico");
            }

        }
        function addAnswer(form) {
            if (pAnswer == null) {
                pAnswer = document.getElementById("nico");
            }
            var answerDiv = document.createElement("div");
            answerDiv.class = "section";
            var contentP = document.createElement("p");
            var content = form.elements["question_content"];
            contentP.innerHTML = content.value.substring(content.selectionStart, content.selectionEnd);
            console.log(content);
            answerDiv.appendChild(contentP);
            pAnswer.appendChild(answerDiv);
        }
    </script>
</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
        <div class="row">
            <div class="col s0 m1 l1"></div>
            <div class="col s12 m10 l10">
                <div class="section"></div>
                <h3>添加测试</h3>
                <h4>Create Test</h4>
                <div class="divider"></div>
                <div class="section"></div>
                <!-- add options -->
                <form id="addForm" action="#">
                    <!-- global info -->
                    <div class="row">
                        <div class="input-field col s5">
                            <i class="material-icons prefix">subtitles</i>
                            <input id="hw_title" name="testTitle" type="text" class="validate" data-length="15">
                            <label for="hw_title">测试名称</label>
                        </div>
                        <div class="col s1"></div>
                        <div class="input-field col s5">
                            <i class="material-icons prefix">date_range</i>
                            <input id="hw_ddl" name="ddlDate" type="text" class="datepicker">
                            <label for="hw_ddl">截止日期</label>
                        </div>
                    </div><br>
                    <!-- add info -->
                    <div class="row">
                        <div class="col s1 m0 l0"></div>
                        <div class="col s12">
                            <h5>添加新题目</h5>
                            <p>
                                <input name="question_type" type="radio" id="choice" value="choice" onclick="show_choice()" checked />
                                <label for="choice">选择题</label>
                            </p>
                            <p>
                                <input name="question_type" type="radio" id="answer" value="answer" onclick="show_answer()"/>
                                <label for="answer">简答题</label>
                            </p>
                        </div>
                        <div id="add-choice">
                            <div class="row">
                                <div class="col s1 m0 l0"></div>
                                <div class="col s12">
                                    <div class="input-field col s12">
                                        <label for="question_content">选择题题干</label>
                                        <textarea type="text" id="question_content" name="question_content" class="materialize-textarea"></textarea>
                                        <div class="input-field col s12">
                                            <div class="input-field col s6">
                                                <input id="add_option1_text" type="text" class="validate">
                                                <label for="add_option1_text">选项1</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="add_option2_text" type="text" class="validate">
                                                <label for="add_option2_text">选项2</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="add_option3_text" type="text" class="validate">
                                                <label for="add_option3_text">选项3</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="add_option4_text" type="text" class="validate">
                                                <label for="add_option4_text">选项4</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <select id="right_answer">
                                                    <option value="1">选项1</option>
                                                    <option value="2">选项2</option>
                                                    <option value="3">选项3</option>
                                                    <option value="4">选项4</option>
                                                </select>
                                                <label>正确选项</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="add-answer">
                            <div class="row">
                                <div class="col s1 m0 l0"></div>
                                <div class="col s12">
                                    <div class="input-field col s12">
                                        <textarea type="text" id="question_content" name="question_content" class="materialize-textarea"></textarea>
                                        <label for="question_content">简答题题干</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s0"></div>
                            <div class="input-field col s6">
                                <i class="material-icons prefix">grade</i>
                                <input id="hw_score" name="hw_score" type="number" class="validate" min="0" max="100">
                                <label for="hw_score">作业分数</label>
                            </div><br>
                            <div class="col s5">
                                <button class="btn waves-effect waves-light" type="reset"
                                        name="action" style="float:right;" onclick="addQuestion()">
                                    添加题目
                                </button>
                            </div>
                        </div>
                    </div><br>
                    <div class="divider"></div>
                </form>
                <!-- current questions -->
                <form id="finalForm">
                    <div class="row">
                        <div class="col s1 m0 l0"></div>
                        <div class="col s12">
                            <div class="section"></div>
                            <h5>已添加的题</h5>
                            <div class="section">
                                <p>1. 这是选择题的题干</p>
                                <p>
                                    <input name="group2" type="radio" id="option1" disabled="disabled"/>
                                    <label for="option1">选项1</label>
                                </p>
                                <p>
                                    <input name="group2" type="radio" id="option2" disabled="disabled"/>
                                    <label for="option2">选项2</label>
                                </p>
                                <p>
                                    <input name="group2" type="radio" id="option3" disabled="disabled"/>
                                    <label for="option3">选项3</label>
                                </p>
                                <p>
                                    <input name="group2" type="radio" id="option4" disabled="disabled"/>
                                    <label for="option4">选项4</label>
                                </p>
                            </div>
                            <div class="section">
                                <p>2. 这是主观题的题干</p>
                            </div>
                        </div><br>
                        <div class="row">
                            <div id="nico"></div>
                        </div>
                </form>
                <div class="col s0 m1 l1"></div>
            </div>
        </div>
    </div>
</tmpl:overwrite>..
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>