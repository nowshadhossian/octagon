<#-- @ftlvariable name="assistant" type="com.kids.crm.model.Assistant" -->
<#import "/spring.ftl" as spring/>
<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/super-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Assistant</h1>
        <form id="subTopicForm" action="/superadmin/assistant/save" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${(assistant.getId())!""}"/>

            <@spring.bind "assistant.firstName" />
            <div class="form-group row">
                <label for="firstName" class="col-sm-3 col-form-label">First Name</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="firstName" name="${spring.status.expression}" value="${spring.status.value!""}" required>
                </div>
            </div>

              <@spring.bind "assistant.lastName" />
            <div class="form-group row">
                <label for="lastName" class="col-sm-3 col-form-label">Last Name</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="lastName" name="${spring.status.expression}" value="${spring.status.value!""}" required>
                </div>
            </div>

            <@spring.bind "assistant.email" />
            <div class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-8">
                    <input type="text" name="${spring.status.expression}" value="${spring.status.value!""}" class="form-control" id="email" required>
                </div>
            </div>

            <@spring.bind "assistant.phone" />
            <div class="form-group row">
                <label for="phone" class="col-sm-3 col-form-label">Phone</label>
                <div class="col-sm-8">
                    <input type="text" name="${spring.status.expression}" value="${spring.status.value!""}" class="form-control" id="phone">
                </div>
            </div>

             <@spring.bind "assistant.password" />
            <div class="form-group row">
                <label for="password" class="col-sm-3 col-form-label">Password</label>
                <div class="col-sm-8">
                    <input type="text" name="${spring.status.expression}" value="${spring.status.value!""}" class="form-control" id="password">
                </div>
            </div>

            <button type="button" id="cancelButton" class="btn btn-primary" onclick="window.location.href='/superadmin/assistant/list'">Cancel</button>
            <button type="submit" id="submitForm" class="btn btn-primary"><#if assistant.getId()??>Save<#else>Add</#if> Assistant</button>
        </form>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">