<#-- @ftlvariable name="leaderboardPage" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->

<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/teacher-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Hello ${name}!!!</h1>
        <#include leaderboardPage>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">


