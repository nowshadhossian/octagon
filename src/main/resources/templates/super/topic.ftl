<#-- @ftlvariable name="leaderboardPage" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->

<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Topic</h1>
        <div>
            <a href="/superadmin/topic/add" class="btn btn-dark">Add Topic</a>
        </div>
        <div>
            <div>
                Topics
            </div>
            <div>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Sub-Topic</th>
                        <th scope="col">Subject/course</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list topics as topic>
                        <tr>
                            <td>${topic.id}</td>
                            <td>${topic.name}</td>
                            <td>${abbreviateSubTopicMap["topicId_"+topic.id]}</td>
                            <td><#if topic.subject??>${topic.subject.name}</#if></td>
                            <td><a href="/superadmin/topic/${topic.id}/edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">