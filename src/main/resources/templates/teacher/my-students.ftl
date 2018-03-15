<#-- @ftlvariable name="students" type="java.util.List<com.kids.crm.model.Student>" -->
<#-- @ftlvariable name="batch" type="com.kids.crm.model.Batch" -->

<#assign title="My Students | Octagon">
<#assign navPage="/layout/nav/teacher-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>${batch.getSubject().getName()} ${batch.getSession().getName()} - ${batch.getSession().getYear()?c}</h1>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> My Students
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Sl#</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list students as student>
                        <tr>
                            <td>${student?counter}</td>
                            <td>${student.getName()}</td>
                            <td>${student.getPhone()!""}</td>
                            <td>${student.getUsername()}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">


