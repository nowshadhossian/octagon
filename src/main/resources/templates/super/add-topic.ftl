<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div>
    <form id="addTopicForm" action="/superadmin/topic/add" method="post">
        <div class="form-group row">
            <label for="topicName" class="col-sm-3 col-form-label">Name</label>
            <div class="col-sm-8">
                <input type="text" name="name" class="form-control" id="topicName" placeholder="Topic Name">
            </div>
        </div>
        <div class="form-group row">
            <label for="subject" class="col-sm-3 col-form-label">Subject</label>
            <div class="col-sm-8">
                <select name="subject.id" class="selectpicker show-tick">
                    <option value="">Select</option>
                    <#list model["allSubject"] as subject>
                        <option value="${subject.id}">${subject.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" id="submitForm" class="btn btn-primary">Add Topic</button>
    </form>
</div>

<#include "/layout/nav/bottom.ftl">