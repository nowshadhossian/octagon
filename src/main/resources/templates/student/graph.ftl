<#-- @ftlvariable name="topics" type="java.util.List<com.kids.crm.model.Topic>" -->
<#-- @ftlvariable name="years" type="java.util.List<java.lang.Integer>" -->
<#-- @ftlvariable name="leaderboardTodayPageToInclude" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Custom Exam Settings | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Hello!!</h1>
        <canvas id="studentDailyRightWrong" width="550" height="405"></canvas>

        <div class="second-graph">
            <div>
                <table width=50% >
                    <thead>
                    <tr id="marksTableHead"></tr>
                    </thead>
                    <tbody id="marksTableBody">

                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">

<script src="/js/graph.js"></script>

