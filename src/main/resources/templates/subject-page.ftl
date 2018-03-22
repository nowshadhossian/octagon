<#-- @ftlvariable name="leaderboardTodayPageToInclude" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Hello ${name}!!!</h1>
        <p>This is an example of a blank page that you can use as a starting point for creating new ones.</p>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> Last Week Results
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Sl#</th>
                            <th>Correct</th>
                            <th>Wrong</th>
                            <th>AttendedOn</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list lastWeeklyResults as result>
                            <tr>
                                <td>${result?counter}</td>
                                <td>${result.getCorrect()}</td>
                                <td>${result.getWrong()}</td>
                                <td>${result.getDate()}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
            <div class="card-footer small text-muted"><a href="/student/stats/daily-results">Show All</a></div>
        </div>

        <#include leaderboardTodayPageToInclude>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">

