<#-- @ftlvariable name="leaderboardPage" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->

<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>File Upload</h1>
        <div>
            <a href="/superadmin/question/upload">Question Upload Start Now</a>
        </div>

        <form action="/logout" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input class="btn btn-primary" type="submit" value="Logout">
        </form>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">


