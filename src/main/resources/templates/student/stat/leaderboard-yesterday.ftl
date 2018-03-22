<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Leaderboard Yesterday | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">

       <#include leaderboardYesterdayForInclude/>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">

