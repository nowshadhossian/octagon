<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <#if question.id??>
            <h1>Edit Qestion</h1>
        <#else>
            <h1>Upload Qestion</h1>
            <h4>You can upload new question here...</h4>
        </#if>
        <form action="/superadmin/questions/save" method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${(question.id)!""}">
            <#if question.id??>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>Uploaded Question</label>
                    </div>
                    <div class="form-group col-md-4">
                        <div class="thumbnail">
                            <#if question.fileName??>
                                <img src="/files/load-image/${question.fileName}" alt="Lights" style="width:100%">
                            <#else >
                                <p class="text-md-left">Question not uploaded</p>
                            </#if>
                        </div>
                    </div>
                </div>
            </#if>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Upload Question</label>
                </div>
                <div class="form-group col-md-4">
                    <input type="file" name="file"/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="curriculum">Curriculum</label>
                    <input class="form-control" id="curriculum" name="curriculum" type="text"
                           value="${question.curriculum!""}" aria-describedby="nameHelp"
                           placeholder="Curriculum" required>
                    <label for="year">Year</label>
                    <select class="form-control" id="year" name="year" required>
                        <option>Select Year...</option>
                        <#list 1980..2030 as i>
                            <option value="${i}" <#if question.year?? && question.year==i>selected</#if> >${i}</option>
                        </#list>
                    </select>
                    <label for="session">Session</label>
                    <select class="form-control" id="subject" name="session" required>
                        <option value="">Select session...</option>
                        <#list sessions as session>
                            <option value="${session.id}" <#if question.session?? && session.id==question.session.id>selected</#if> >${session.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group col-md-8">
                    <label for="answerNotes">Answer notes</label>
                    <textarea class="form-control" rows="6" id="answerNotes"  name="answerExplanation" value="${question.note!""}"></textarea>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Variant</label>
                    <input class="form-control" id="variant" name="variant" type="text" pattern="\d*" maxlength="2" minlength="2" title="Enter 2 digit number"
                           value="${question.variant!""}" aria-describedby="nameHelp"
                           placeholder="Enter 2 digit" required>
                </div>
                <div class="form-group col-md-4 date_picker">
                    <label>Upload Date</label>
                    <div id="upload-date">${question.uploadDate!""}</div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="questionNo">Question No</label>
                    <select class="form-control" id="questionNo" name="questionNo" required>
                        <option value="">Select Question No...</option>
                        <#list 1..100 as i>
                            <option value="${i}" <#if question.questionNo?? && question.questionNo==i>selected</#if> >${i}</option>
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
                    <select class="form-control" id="subject" name="subject" required>
                        <option value="">Select Subject...</option>
                        <#list subjects as subject>
                            <option value="${subject.id}" <#if question.subject?? && question.subject.id==subject.id>selected</#if> >${subject.name}</option>
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
                    <select class="form-control" id="topic" name="topic" required>
                        <option value="">Select Topic...</option>
                        <#list topics as topic>
                            <option value="${topic.id}" <#if question.topic?? && question.topic.id==topic.id>selected</#if> >${topic.name}</option>
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
                        <option value="">Select Sub-topic...</option>

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
                        C <input type="checkbox" id="optionC" name="answer" value="C" />
                        D <input type="checkbox" id="optionD" name="answer" value="D" />
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
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Version</label>
                    <select class="form-control" id="version" name="version" required>
                        <option>Select version</option>
                        <option value="1">Bengali</option>
                        <option value="2">English</option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="active">Active</label>
                    <div>
                        <input type="checkbox" id="active" name="active"/>
                    </div>
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


