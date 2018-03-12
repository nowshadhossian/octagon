<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Subject Home | Octagon">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> Last Week Results
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Sl#</th>
                        <th>Correct</th>
                        <th>Wrong</th>
                        <th>AttendedOn</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list lastWeeklyResults as result>
                        <tr>
                            <td>${result?counter}</td>
                            <td>${result.getCorrect()}</td>
                            <td>${result.getWrong()}</td>
                            <td>${result.getDate()}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">

