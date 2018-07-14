<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Subject</h1>
        <div>
            <a href="/superadmin/subject/add" class="btn btn-dark">Add Subject</a>
        </div>
        <div>
            <div>
                Subjects
            </div>
            <div>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Code</th>
                        <th scope="col">Subject</th>
                        <th scope="col">Board</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list subjects as subject>
                    <tr>
                        <td>${subject.code}</td>
                        <td>${subject.name}</td>
                        <td></td>
                        <td><#if subject.board??>${subject.board.name}</#if></td>
                        <td></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
<#include "/layout/nav/bottom.ftl">