<#-- @ftlvariable name="studentBatches" type="java.util.List<com.kids.crm.model.StudentBatch>" -->
<#-- @ftlvariable name="students" type="java.util.List<com.kids.crm.model.Student>" -->
<#-- @ftlvariable name="students" type="java.util.List<com.kids.crm.model.Student>" -->
<#-- @ftlvariable name="myStudents" type="java.util.List<com.kids.crm.model.Student>" -->
<#-- @ftlvariable name="batch" type="com.kids.crm.model.Batch" -->

<#assign title="My Students | Octagon">
<#assign navPage="/layout/nav/teacher-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>${batch.getSubject().getName()} ${batch.getSession().getName()} - ${batch.getSession().getYear()?c}</h1>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> All Students
            </div>
            <div class="card-body" style="overflow: auto">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Sl#</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list studentBatches as studentBatch>
                        <#assign student = studentBatch.getStudent()>
                        <tr>
                            <td>${studentBatch?counter}</td>
                            <td>${student.getName()}</td>
                            <td>${student.getPhone()!""}</td>
                            <td>${student.getUsername()}</td>
                            <td>${studentBatch.getBatchStatusType()}</td>
                            <td>
                                <#if studentBatch.getBatchStatusType().isPending()>
                                    <form action="/teacher/students/add" method="POST">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="student" value=${student.getId()}>
                                        <button class="btn-primary">Approve</button>
                                    </form>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">


