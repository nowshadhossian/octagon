<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Question Upload</h1>
        <h4>You can upload new question here...</h4>
        <form action="/superadmin/question-upload" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input class="btn btn-primary" type="submit" name="medical-db-init" value="Initialize">
        </form>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">


