<#-- @ftlvariable name="leaderboardYesterdayResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#-- @ftlvariable name="loggedIn" type="com.kids.crm.model.User" -->
<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i> Leaderboard
    </div>
    <div class="card-body" style="overflow: auto">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Sl#</th>
                <th>Name</th>
                <th>Correct</th>
                <th>Wrong</th>
                <th>AttendedOn</th>
            </tr>
            </thead>
            <tbody>
                        <#list leaderboardYesterdayResults as leaderboardAttendedResult>
                        <tr>
                            <td>${leaderboardAttendedResult?counter}</td>
                            <td>${leaderboardAttendedResult.getUser().getName()}</td>
                            <td>${leaderboardAttendedResult.getCorrect()}</td>
                            <td>${leaderboardAttendedResult.getWrong()}</td>
                            <td>${leaderboardAttendedResult.getDate()}</td>
                        </tr>
                        </#list>
            </tbody>
        </table>
    </div>
    <#if loggedIn.getRole().isStudent()>
        <#assign yesterdayLeaderboardUrl="/student/stats/leaderboard-yesterday-results">
    <#elseif loggedIn.getRole().isTeacher()>
        <#assign yesterdayLeaderboardUrl="/teacher/student/leaderboard-yesterday-results">
    </#if>
    <div class="card-footer small text-muted"><a href="${yesterdayLeaderboardUrl!""}">Yesterday</a></div>
</div>