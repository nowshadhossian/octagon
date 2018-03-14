<#-- @ftlvariable name="leaderboardYesterdayResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i> Leaderboard
    </div>
    <div class="card-body">
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
    <div class="card-footer small text-muted"><a href="/student/stats/daily-results">Show All</a></div>
</div>