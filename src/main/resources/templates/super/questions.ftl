<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="row">
    <div class="col-12">
        <h1>Question</h1>
        <div>
            <a href="/superadmin/questions/upload" class="btn btn-dark">Upload Question</a>
        </div>
        <div>
            <div>
                Questions
            </div>
            <div>
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
                    <tbody>
                    <#list allQuestions as question>
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