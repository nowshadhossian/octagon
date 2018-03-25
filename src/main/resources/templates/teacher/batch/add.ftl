<#-- @ftlvariable name="sessions" type="java.util.List<java.lang.String>" -->
<#-- @ftlvariable name="years" type="java.util.List<java.lang.Integer>" -->
<#-- @ftlvariable name="subjects" type="java.util.List<com.kids.crm.model.Subject>" -->
<#import "/spring.ftl" as spring/>
<#assign title="Batch Add | Octagon">
<#include "/layout/no-nav/top.ftl">
<div class="card card-register mx-auto mt-5">
    <div class="card-header">Add Batch</div>
    <div class="card-body">
        <form action="/teacher/batch/add" method="post">

            <div class="form-group">
                <label for="session">Session</label>
                <select class="form-control" id="session" name="session">
                        <#list sessions as session>
                            <option value="${session}">${session}</option>
                        </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="subject">Subject</label>
                <select class="form-control" id="subject" name="subject">
                        <#list subjects as subject>
                            <option value="${subject.getId()}">${subject.getName()}</option>
                        </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="year">Year</label>
                <select class="form-control" id="year" name="year">
                        <#list years as year>
                            <option value="${year?c}">${year?c}</option>
                        </#list>
                </select>
            </div>


            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button id="save" type="submit" class="btn btn-primary btn-block" name="save" value="save">
                Save
            </button>
        </form>
        <div class="text-center">
            <a class="d-block small mt-3" href="/login">Login Page</a>
            <#--<a class="d-block small" href="/forgot-password">Forgot Password?</a>-->
        </div>
    </div>

</div>
<#include "/layout/no-nav/bottom.ftl">
