<#-- @ftlvariable name="loggedIn" type="com.kids.crm.model.User" -->
<#if LOGGED_IN_USER.getRole().isStudent()>
    <#assign navPage="/layout/nav/student-nav.ftl">
<#elseif LOGGED_IN_USER.getRole().isTeacher()>
    <#assign navPage="/layout/nav/teacher-nav.ftl">
<#elseif LOGGED_IN_USER.getRole().isAssistant()>
    <#assign navPage="/layout/nav/assistant-nav.ftl">
<#elseif LOGGED_IN_USER.getRole().isSuperAdmin()>
    <#assign navPage="/layout/nav/super-nav.ftl">
</#if>