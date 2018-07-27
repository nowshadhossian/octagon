<#-- @ftlvariable name="myStudentBatches" type="java.util.List<com.kids.crm.model.StudentBatch>" -->
<#import "/spring.ftl" as spring/>
<#assign title="Select Batch | Octagon">
<#include "/layout/no-nav/top.ftl">
<div class="row" style="margin-top: 220px">
    <#list myStudentBatches as studentBatch>
        <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fa fa-fw fa-comments"></i>
                    </div>
                    <div class="mr-5">${studentBatch.getBatch().getSubject().getName()} ${studentBatch.getBatch().getSession().getName()} - ${studentBatch.getBatch().getSession().getYear()?c}</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="/student/batch/${studentBatch.getId()}">
                    <span class="float-left">Total Students: ${studentBatch.getBatch().getStudents()?size}</span>
                    <span class="float-right"> ${studentBatch.getBatchStatusType()}
                    <i class="fa fa-angle-right"></i>
                  </span>
                </a>
            </div>
        </div>
    <#else>
    <h1 class="text-white">
        Ask your teacher for enrolment
    </h1>
    </#list>

</div>

<div class="row" style="margin-top: 220px">
    <form action="/logout" method="post">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input class="btn btn-primary" type="submit" value="Logout">
    </form>
</div>
<#include "/layout/no-nav/bottom.ftl">
