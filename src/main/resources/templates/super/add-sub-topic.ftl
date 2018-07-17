<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1><#if subTopic.id??>Edit<#else>Add</#if> Sub-Topic</h1>
        <form id="subTopicForm" action="/superadmin/sub-topic/save" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${(subTopic.id)!""}"/>
            <input type="hidden" name="topic.id" value="${(subTopic.topic.id)!topic.id}">
            <div class="form-group row">
                <label for="topicName" class="col-sm-3 col-form-label">Topic Name</label>
                <div class="col-sm-8">
                    <label>${(subTopic.topic.name)!topic.name}</label>
                </div>
            </div>
            <div class="form-group row">
                <label for="subTopicName" class="col-sm-3 col-form-label">Name</label>
                <div class="col-sm-8">
                    <input type="text" name="name" value="${(subTopic.name)!""}" class="form-control" id="subTopicName" placeholder="Sub-Topic Name" required>
                </div>
            </div>
            <button type="button" id="cancelButton" class="btn btn-primary" onclick="window.location.href='/superadmin/topic/${(subTopic.topic.id)!topic.id}/edit'">Cancel</button>
            <button type="submit" id="submitForm" class="btn btn-primary"><#if subTopic.id??>Save<#else>Add</#if> Sub-Topic</button>
        </form>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">