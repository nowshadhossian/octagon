<#-- @ftlvariable name="loggedIn" type="com.kids.crm.model.User" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Leaderboard Yesterday | Octagon">
<#if loggedIn.getRole().isStudent()>
    <#assign navPage="/layout/nav/student-nav.ftl">
<#elseif loggedIn.getRole().isTeacher()>
    <#assign navPage="/layout/nav/teacher-nav.ftl">
</#if>
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">

       <#include leaderboardYesterdayForInclude/>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">

