<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Question Upload</h1>
        <h4>You can upload new question here...</h4>
        <form action="/superadmin/question-upload" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="firstName">Curriculum</label>
                    <input class="form-control" id="curriculum" name="curriculum" type="text"
                           value="test" aria-describedby="nameHelp"
                           placeholder="Enter first name">
                    <label for="exampleFormControlSelect1">Year</label>
                    <select class="form-control" id="year" name="year">
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                    </select>
                </div>
                <div class="form-group col-md-8">
                    <label for="firstName">Answer notes</label>
                    <textarea class="form-control" rows="4" id="answerNotes"  name="answerNotes" value="answerNotes"></textarea>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="exampleFormControlSelect1">Session</label>
                    <select class="form-control" id="session" name="session">
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                    </select>
                </div>
                <div class="form-group col-md-4 date_picker">
                    <label>Upload Date</label>
                    <input class="form-control" id="uploadDate" name="uploadDate" value=""
                           placeholder="MM/DD/YYYY enter date" type="text"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Variant</label>
                    <input class="form-control" id="variant" name="variant" type="text"
                           value="test" aria-describedby="nameHelp"
                           placeholder="Enter first name">
                </div>
                <div class="form-group col-md-4">
                    <label>Total Attempt</label>
                    <input class="form-control" id="totalAttempt" name="totalAttempt" type="text"
                           value="test" aria-describedby="nameHelp"
                           placeholder="Enter first name">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                        <label>Question</label>
                        <input class="form-control" id="question" name="question" type="text"
                               value="test" aria-describedby="nameHelp"
                               placeholder="Enter first name">
                </div>
                <div class="form-group col-md-4">
                    <label>Skip Frequency</label>
                    <input class="form-control" id="skipFrequency" name="skipFrequency" type="text"
                           value="test" aria-describedby="nameHelp"
                           placeholder="Skip Frequency">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="exampleFormControlSelect1">Topic</label>
                    <select class="form-control" id="topic" name="topic">
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label>Flag Count</label>
                    <input class="form-control" id="flagCount" name="flagCount" type="text"
                           value="test" aria-describedby="nameHelp"
                           placeholder="Skip Frequency">
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
                    <input class="form-control" id="flagMessage" name="flagMessage" type="text"
                           value="test" aria-describedby="nameHelp"
                           placeholder="Skip Frequency">
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


