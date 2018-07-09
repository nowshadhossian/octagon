<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Question Upload</h1>
        <h4>You can upload new question here...</h4>
        <form action="/superadmin/question-upload" method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Upload Question</label>
                </div>
                <div class="form-group col-md-4">
                    <input type="file" name="file" />
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="curriculum">Curriculum</label>
                    <input class="form-control" id="curriculum" name="curriculum" type="text"
                           value="" aria-describedby="nameHelp"
                           placeholder="Curriculum" required>
                    <label for="year">Year</label>
                    <select class="form-control" id="year" name="year">
                        <#list 1980..2030 as i>
                            <option value="${i}">${i}</option>
                        </#list>
                    </select>
                    <label for="session">Session</label>
                    <input class="form-control" id="session" name="session" type="text" pattern="\d*" maxlength="4" minlength="4" title="Enter 4 digit DDMM Pattern"
                           value="" aria-describedby="nameHelp"
                           placeholder="Enter DDMM" required>
                </div>
                <div class="form-group col-md-8">
                    <label for="answerNotes">Answer notes</label>
                    <textarea class="form-control" rows="6" id="answerNotes"  name="answerNotes" value=""></textarea>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Variant</label>
                    <input class="form-control" id="variant" name="variant" type="text" pattern="\d*" maxlength="2" minlength="2" title="Enter 2 digit number"
                           value="" aria-describedby="nameHelp"
                           placeholder="Enter 2 digit" required>
                </div>
                <div class="form-group col-md-4 date_picker">
                    <label>Upload Date</label>
                    <div id="upload-date"></div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="questionNo">Question No</label>
                    <select class="form-control" id="questionNo" name="questionNo">
                            <#list 1..100 as i>
                                <option value="${i}">${i}</option>
                            </#list>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label>Total Attempts</label>
                    <div id="totalAttempt"></div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="subject">Subject</label>
                    <select class="form-control" id="subject" name="subject">
                        <#list model["allSubject"] as subject>
                            <option value="${subject.id}">${subject.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label>Skip Frequency</label>
                    <div id="skipFrequency"></div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="topic">Topic</label>
                    <select class="form-control" id="topic" name="topic">
                        <#list model["allTopic"] as topic>
                            <option value="${topic.id}">${topic.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label>Flag Count</label>
                    <div id="flag-count"></div>
                </div>
            </div>


            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="exampleFormControlSelect1">Sub topic</label>
                    <select class="form-control" id="subTopic" name="subTopic">
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label>Flag Message</label>
                    <div id="flagMessage"></div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="correctAnswers">Correct Answers: </label>
                    <div>
                        A <input type="checkbox" id="optionA" name="answer" value="A" />
                        B <input type="checkbox" id="optionB" name="answer" value="B" />
                        B <input type="checkbox" id="optionC" name="answer" value="C" />
                        C <input type="checkbox" id="optionD" name="answer" value="D" />
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label>Answer Duration</label>
                    <div id="answerDuration"></div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="answeredQuestion">Question Answered</label>
                    <div id="answeredQuestion"></div>
                </div>
                <div class="form-group col-md-4">
                    <label for="skippedQuestion">Question Skipped</label>
                    <div id="skippedQuestion"></div>
                </div>
            </div>

            <input class="btn btn-primary" type="submit" name="medical-db-init" value="Upload Question">
        </form>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">
<script>
    $( function() {
        $( "#uploadDate" ).datepicker();
    } );
</script>


