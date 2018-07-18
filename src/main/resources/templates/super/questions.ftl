<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="row">
    <div class="col-12">
        <h1>Question</h1>

        <div class="form-row">
            <div class="form-group col-md-6">
                <a href="/superadmin/questions/upload" class="btn btn-dark">Upload Question</a>
            </div>
            <div class="form-group col-md-6">
                <div class="input-group col-sm-6 float-right-md padding-left-sm-0">
                    <input type="text" id="searchQId" class="form-control" placeholder="Search by Year YYYY" aria-label="Year" pattern="\d*" maxlength="4" minlength="4" title="Enter 4 digit year">
                    <div class="input-group-append">
                        <button class="btn btn-dark" id="searchQuestion" type="button">Search</button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div>
                Questions
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Subject/course</th>
                        <th scope="col">Topic</th>
                        <th scope="col">Year</th>
                        <th scope="col">Session</th>
                        <th scope="col">Question No</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody id="questionList">
                    <#list questions as question>
                    <tr>
                        <td><#if question.subject??>${question.subject.name}</#if></td>
                        <td><#if question.topic??>${question.topic.name}</#if></td>
                        <td>${question.year}</td>
                        <td>${(question.session.name)!""}</td>
                        <td>${question.questionNo}</td>
                        <td><a href="/superadmin/questions/${question.id}/edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

<#include "/layout/nav/bottom.ftl">

<script>
    $( function() {

        $("#searchQuestion").click(function () {
            var year = $("#searchQId").val();
            if(!($.isNumeric(year)) || year<1 || !(year.toString().length==4)){
                return false;
            }
            var getUrl = '/superadmin/questions/serarchQuestionsByYear?year='+year;
            $.ajax({
                url: getUrl,
                dataType: 'json',
                success: function(data)
                {
                    populateQuestionList(data);
                }
            });
        });

        function populateQuestionList(data) {
            $("#questionList").html("");
            $.each( data, function( key, value ) {
                $("#questionList").append('<tr><td>'+value.subject.name+'</td><td>'+value.topic.name+'</td><td>'+value.year+'</td><td>'+value.session.name+'</td><td>'+value.questionNo+'</td><td><a href="/superadmin/questions/'+value.id+'/edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td></tr>');
            });
        }
    } );
</script>