<#-- @ftlvariable name="leaderboardPage" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->

<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1 class="text-danger">Danger Zone</h1>
        <div>
            Initialize Medical DB
        </div>

        <form action="/superadmin/danger-zone" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input class="btn btn-primary" type="submit" name="medical-db-init" value="Initialize">
        </form>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">


