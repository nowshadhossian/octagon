<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1><#if topic.id??>Edit<#else>Add</#if> Topic</h1>
        <form id="addTopicForm" action="/superadmin/topic/save" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${(topic.id)!""}"/>
            <div class="form-group row">
                <label for="topicName" class="col-sm-3 col-form-label">Name</label>
                <div class="col-sm-8">
                    <input type="text" name="name" class="form-control" value="${(topic.name)!""}" id="topicName" placeholder="Topic Name" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="subject" class="col-sm-3 col-form-label">Subject</label>
                <div class="col-sm-8">
                    <select name="subject.id" class="selectpicker show-tick">
                        <option value="">Select</option>
                        <#list subjects as subject>
                            <option value="${subject.id}" <#if topic.subject??><#if topic.subject.id==subject.id>selected</#if></#if> >${subject.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <#if topic.id??>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <div>Sub Topics</div>
                        <div>
                            <a href="/superadmin/sub-topic/add?topicId=${topic.id}" class="btn btn-dark">Add Topic</a>
                        </div>
                        <table class="table">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list subTopics as subTopic>
                                    <tr>
                                        <td>${subTopic.name}</td>
                                        <td><a href="/superadmin/sub-topic/edit?subTopicId=${subTopic.id}" title="Edit Sub-topic"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </#if>
            <button type="submit" id="submitForm" class="btn btn-primary"><#if topic.id??>Save<#else>Add</#if> Topic</button>
        </form>
    </div>
</div>

<#include "/layout/nav/bottom.ftl">