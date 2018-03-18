<#-- @ftlvariable name="myBatches" type="java.util.Set<com.kids.crm.model.Batch>" -->
<#import "/spring.ftl" as spring/>
<#assign title="Select Batch | Octagon">
<#include "/layout/no-nav/top.ftl">
<div class="row" style="margin-top: 220px">
    <#list myBatches as batch>
        <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fa fa-fw fa-comments"></i>
                    </div>
                    <div class="mr-5">${batch.getSubject().getName()} ${batch.getSession().getName()} - ${batch.getSession().getYear()?c}</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="/student/batch/${batch.getId()}">
                    <span class="float-left">Total Students: ${batch.getStudents()?size}</span>
                    <span class="float-right">
                    <i class="fa fa-angle-right"></i>
                  </span>
                </a>
            </div>
        </div>
    <#else>
    <h1 class="text-white">
        Ask your teacher for enrolment
        <p>
            <form action="/logout" method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input class="btn btn-primary" type="submit" value="Logout">
            </form>
        </p>
    </h1>

    </#list>
</div>
<#include "/layout/no-nav/bottom.ftl">
