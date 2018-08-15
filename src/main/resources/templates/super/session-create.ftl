<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div>
    <h1>Add Session</h1>
    <form id="addTopicForm" action="/superadmin/session/create" method="post">
        <div class="form-group row">
            <label for="sessionName" class="col-sm-3 col-form-label">Session Name</label>
            <div class="col-sm-8">
                <input type="text" name="name" class="form-control" value="${session.name!""}" id="sessionName" placeholder="Session Name" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="year" class="col-sm-3 col-form-label">Year</label>
            <div class="col-sm-8">
                <select class="form-control" id="year" name="year">
                        <#list 1980..2030 as i>
                            <option value="${i}" <#if session.year?? && session.year==i>selected</#if>>${i}</option>
                        </#list>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label for="examDate" class="col-sm-3 col-form-label">Examination Date: </label>
            <div class="col-sm-8">
                <input type="text" name="examDate" class="form-control" id="examDate" autocomplete="off">
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="id" value="${(session.id)!""}" />
        <button type="button" id="cancelButton" class="btn btn-primary" onclick="window.location.href='/superadmin/session'">Cancel</button>
        <button type="submit" id="submitForm" class="btn btn-primary">Add Session</button>
    </form>
</div>

<#include "/layout/nav/bottom.ftl">
<script>
    $( function() {
        $("#examDate").datetimepicker({
            format:'M d, Y, h:00 a' // datetime picker format
        });
        var dateTime = moment('${session.examDate!""}').format("MMM DD, Y, h:00 a"); // moment js format
        $("#examDate").val(dateTime);
        $("#examDate").datetimepicker("setValue",dateTime);
    });
</script>