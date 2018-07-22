<#-- @ftlvariable name="topics" type="java.util.List<com.kids.crm.model.Topic>" -->
<#-- @ftlvariable name="years" type="java.util.List<java.lang.Integer>" -->
<#-- @ftlvariable name="leaderboardTodayPageToInclude" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Custom Exam Settings | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">
<div class="row">
    <div class="col-12">
        <h1>Hello ${name}!!!</h1>
        <p>Work on your weakness. Set custom exam</p>
            <form method="post">

                <div class="form-group">
                    <label for="topic">Topic</label>
                    <select id="topic" class="form-control" name="topicId">
                        <option value="-1">Any</option>
                        <#list topics as topic>
                            <option value="${topic.getId()}">${topic.getName()}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for"year">Year(Optional)</label>
                    <select id="year" class="form-control" name="year">
                        <option value="-1">Any</option>
                        <#list years as year>
                            <option value="${year}">${year}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="count">Number of questions</label>
                    <input id="count" class="form-control" type="number" max="50" min="10" name="questionCount"/>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input larger-checkbox" type="checkbox" name="showAnswersInTheEnd"> Show Answers in the end</label>
                    </div>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Start Now</button>
            </form>
    </div>
</div>
<#include "/layout/nav/bottom.ftl">

