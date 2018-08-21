<#-- @ftlvariable name="topics" type="java.util.List<com.kids.crm.model.Topic>" -->
<#-- @ftlvariable name="years" type="java.util.List<java.lang.Integer>" -->
<#-- @ftlvariable name="leaderboardTodayPageToInclude" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Custom Exam Settings | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="row">
    <div class="col-12">
        <h1>Hello!!</h1>
        <div >
            <div   style="float: left">
                <canvas id="studentDailyRightWrong" width="550" height="405"></canvas>
            </div>
            <div   style="float: right">
                <canvas id="radar-chart" width="550" height="405"></canvas>
            </div>
        </div>
    </div>
</div>

<div  style=" max-width: 1400px; max-height: 440px; overflow-x: scroll; overflow-y: scroll; margin-bottom: 30px">
    <table style="border-collapse: separate; border-spacing: 5px  " >
        <thead >
        <tr id="marksTableHead" > </tr>
        </thead>
              <tbody id="marksTableBody"  > </tbody>
     </table>
</div>

<div>
    <div style="height: 440px; width: 750px; background-color: aliceblue; float: left">
        <div style="background-color: #0c5460; width: 750px; height: 40px; text-align: center">
            <h6 style="padding-top: 5px;">Sub Topic Completion</h6>
        </div>
        <div style=" max-height: 400px; max-width: 730px; overflow-y: scroll; float: left;">
            <canvas id="horizontalChart" width="700" height="600" ></canvas>
        </div>
    </div>
    <div style=" max-height: 440px; max-width: 230px; overflow-y: scroll; float: right; margin-right: 230px;">
        <div id="test" style = " height: 800px; width: 200px; background-color: powderblue; "></div>
    </div>
</div>

<div style="margin-top: 480px;">
    <div style="float: left; ">
        <svg id="fillgauge" width="97%" height="250" onclick="gauge1.update(NewValue());"></svg>
        <p <#--id="total" style="margin-left: 610px"-->>total</p>
    </div>
    <div id="liquid" style="width: 1000px; height: 1500px;"> </div>
</div>
<#include "/layout/nav/bottom.ftl">

<script src="/js/graph.js"></script>

