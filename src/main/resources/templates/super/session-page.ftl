<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="row">
    <div class="col-12">
        <h1>Session</h1>
        <div>
            <a href="/superadmin/session/create" class="btn btn-dark">Create Session</a>
        </div>
        <div>
            <div>
                Session
            </div>
            <div>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Year</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list sessions as session>
                    <tr>
                        <td>${session.name}</td>
                        <td>${session.year}</td>
                        <td><a href="/superadmin/session/${session.id}/edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

<#include "/layout/nav/bottom.ftl">