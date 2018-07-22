<#-- @ftlvariable name="assistants" type="java.util.List<com.kids.crm.model.Assistant>" -->
<#-- @ftlvariable name="leaderboardPage" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->

<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Assistants</h1>
        <div>
            <a href="/superadmin/assistant/add" class="btn btn-dark">Add Assistant</a>
        </div>
        <div>
            <div>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list assistants as assitant>
                    <tr>
                        <td>${assitant.getId()}</td>
                        <td>${assitant.getName()}</td>
                        <td>${assitant.getEmail()}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">