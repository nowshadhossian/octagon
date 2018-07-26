<#-- @ftlvariable name="question" type="com.kids.crm.model.Question" -->
<#-- @ftlvariable name="questionStats" type="com.kids.crm.model.mongo.QuestionStats" -->
<#assign title="Question Upload | Octagon">
<#include "/layout/nav/top.ftl">
<#assign skipCount = (questionStats.skipCount)!0>
<#assign totalAttempt = (questionStats.timesAnsweredCount)!0>
<div class="row">
    <div class="col-12">

        <#if question.id??>
            <h1>Edit Upload</h1>
        <#else>
            <h1>New Upload</h1>
            <h4>You can upload new question here...</h4>
        </#if>
        <form action="/assistant/questions/save" method="post" enctype="multipart/form-data">
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
                    <input type="file" name="file" required/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="form-control">Curriculum
                        <select class="form-control" name="curriculum" disabled>
                            <#list curriculums as curriculum>
                                <option value="${curriculum.getId()}" <#if question.curriculum?? && curriculum.id==question.curriculum>selected</#if>>${curriculum.getName()}</option>
                            </#list>
                        </select>
                    </label>

                    <label for="session">Session</label>
                    <select class="form-control" id="subject" name="session" required>
                        <option value="">Select session...</option>
                        <#list sessions as session>
                            <option value="${session.id}" <#if question.session?? && session.id==question.session.id>selected</#if> >Year:${session.year}:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${session.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group col-md-8">
                    <label for="answerNotes">Answer notes</label>
                    <textarea class="form-control" rows="6" id="answerNotes"  name="answerExplanation">${question.answerExplanation!""}</textarea>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="form-control">Variant
                        <select class="form-control" name="variant" disabled>
                            <#list variants as variant>
                                 <option value="${variant.getId()}" <#if question.variant?? && variant.id==question.variant>selected</#if>>${variant.getName()}</option>
                            </#list>
                        </select>
                    </label>
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
                    <div id="totalAttempt">${(questionStats.timesAnsweredCount)!""}</div>
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
                    <div id="skipFrequency"><#if totalAttempt gt 0 >${(skipCount/totalAttempt)*100}<#else ></#if></div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="topic">Topic</label>
                    <select class="form-control" id="topic" name="topic" required>
                        <option value="" disabled selected>Select Topic</option>
                        <#list topics as topic>
                            <option value="${topic.id}" <#if question.topic?? && question.topic.id==topic.id>selected</#if> >${topic.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label>Flag Count</label>
                    <div id="flag-count">${(questionStats.flagCount)!""}</div>
                </div>
            </div>


            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="subTopic">Sub topic</label>
                    <select class="form-control" id="subTopic" name="subTopics" data-placeholder="Select subtopic" multiple required>
                        <#list subTopics as subTopic>
                            <option value="${subTopic.id}" <#if question.subTopics?has_content && question.subTopics?seq_contains(subTopic)>selected</#if>>${subTopic.name}</option>
                        </#list>
                    </select>
                    <small id="emailHelp" class="form-text text-muted">Use Control click to chose multiple</small>
                </div>
                <div class="form-group col-md-4">
                    <label>Flag Message</label>
                    <div id="flagMessage">
                        <#if questionStats??>
                            <ul>
                                <#list questionStats.getFlagMessageCountEmptyIfNull() as key, value>
                                     <li>${key}: ${value}</li>
                                </#list>
                            </ul>
                        </#if>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="correctAnswers">Correct Answers: </label>
                    <div>
                        <div class="form-control-md form-check">
                            <input type="checkbox" class="form-check-input larger-checkbox" name="answer" value="A" id="optionA" <#if question.answer?? && question.answer?contains("A")>checked </#if>>
                            <label class="form-check-label" for="optionA">Option A</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input larger-checkbox" name="answer" value="B" id="optionB" <#if question.answer?? && question.answer?contains("B")>checked</#if>>
                            <label class="form-check-label" for="optionB">Option B</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input larger-checkbox" name="answer" value="C" id="optionC" <#if question.answer?? && question.answer?contains("C")>checked</#if>>
                            <label class="form-check-label" for="optionC">Option C</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input larger-checkbox" name="answer" value="D" id="optionD" <#if question.answer?? && question.answer?contains("D")>checked</#if>>
                            <label class="form-check-label" for="optionD">Option D</label>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="answeredQuestion">Question Answered</label>
                    <div id="answeredQuestion">
                        <#if questionStats??>
                            <ul>
                                 <#list questionStats.getAnsweredCountWithOptionEmptyIfNull() as key, value>
                                     <li>${key}: ${value}</li>
                                 </#list>
                            </ul>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Answer Duration</label>
                    <div id="answerDuration">
                    <#if totalAnswerDuration?? && 0<totalAnswerDuration>${totalAnswerDuration}</#if>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="skippedQuestion">Question Skipped</label>
                    <div id="skippedQuestion">${(questionStats.skipCount)!""}</div>
                </div>

            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Version</label>
                    <select class="form-control" id="version" name="version" required>
                        <option value="" disabled>Select version</option>
                        <option value="0" <#if question.version?? && question.version==0>selected</#if>>English</option>
                        <option value="1" <#if question.version?? && question.version==1>selected</#if>>Bengali</option>
                    </select>
                </div>
                <div class="form-group col-md-4">

                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <div class="form-control-md form-check">
                        <input type="checkbox" class="form-check-input larger-checkbox" id="active" name="active" <#if question.active==true || !question.id??>checked</#if>>
                        <label class="form-check-label" for="active">Active</label>
                    </div>
                </div>
                <div class="form-group col-md-4">

                </div>
            </div>
            <button type="button" id="cancelButton" class="btn btn-primary" onclick="window.location.href='/assistant/questions'">Cancel</button>
            <input class="btn btn-primary" type="submit" name="medical-db-init" value="Upload Question">
        </form>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">
<script>
    $( function() {
        $( "#uploadDate" ).datepicker();

        $("#topic").change(function () {
            var topicId = this.value;
            var getUrl = '/assistant/questions/getSubtopic?topicId='+topicId;
            $.ajax({
                url: getUrl,
                dataType: 'json',
                success: function(data)
                {
                    populateSubtopic(data);
                }
            });
        });

        function populateSubtopic(subTopics) {
            $("#subTopic").html("");
            $.each( subTopics, function( key, value ) {
                $("#subTopic").append("<option value='"+ value.id+"' >"+value.name+"</option>");
            });
        }
    } );
</script>


