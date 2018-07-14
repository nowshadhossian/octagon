<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div>
    <form id="addTopicForm" action="/superadmin/session/create" method="post">
        <div class="form-group row">
            <label for="sessionName" class="col-sm-3 col-form-label">Session Name</label>
            <div class="col-sm-8">
                <input type="text" name="name" class="form-control" id="sessionName" placeholder="Session Name" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="year" class="col-sm-3 col-form-label">Year</label>
            <div class="col-sm-8">
                <select class="form-control" id="year" name="year">
                        <#list 1980..2030 as i>
                            <option value="${i}">${i}</option>
                        </#list>
                </select>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" id="submitForm" class="btn btn-primary">Add Topic</button>
    </form>
</div>

<#include "/layout/nav/bottom.ftl">