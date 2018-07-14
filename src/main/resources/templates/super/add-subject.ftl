<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Add Subject</h1>
        <form id="addSubjectForm" action="/superadmin/subject/add" method="post">
            <div class="form-group row">
                <label for="topicName" class="col-sm-3 col-form-label">Name</label>
                <div class="col-sm-8">
                    <input type="text" name="name" class="form-control" id="subjectName" placeholder="Subject Name" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="code" class="col-sm-3 col-form-label">Code</label>
                <div class="col-sm-8">
                    <input type="text" name="code" class="form-control" id="code" placeholder="Code">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" id="submitForm" class="btn btn-primary">Add Subject</button>
        </form>
    </div>
</div>

<#include "/layout/nav/bottom.ftl">