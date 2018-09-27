<#-- @ftlvariable name="topics" type="java.util.List<com.kids.crm.model.Topic>" -->
<#-- @ftlvariable name="years" type="java.util.List<java.lang.Integer>" -->
<#-- @ftlvariable name="leaderboardTodayPageToInclude" type="java.lang.String" -->
<#-- @ftlvariable name="lastWeeklyResults" type="java.util.List<com.kids.crm.pojo.LastAttendedResult>" -->
<#assign title="Custom Exam Settings | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">
        <h1>Hello!!</h1>
<#--Exam-info start-->
                <div class="card-group" style="margin-bottom: 20px;">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title ">Days to go</h5>
                            <p id="daysToGo" class="card-text" style="font-size: 50px; color: red"></p>
                        </div>
                    <#--<div class="card-footer">-->
                    <#--<small class="text-muted">Last updated 3 mins ago</small>-->
                    <#--</div>-->
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Total score</h5>
                            <p id="totalScore" class="card-text" style="font-size: 50px; color: aqua"></p>
                        </div>
                    <#--<div class="card-footer">-->
                    <#--<small class="text-muted">Last updated 3 mins ago</small>-->
                    <#--</div>-->
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Average time per question</h5>
                            <p id="averageTimePerQuestion" class="card-text" style="font-size: 50px; color: aqua"></p>
                        </div>
                    <#--<div class="card-footer">-->
                    <#--<small class="text-muted">Last updated 3 mins ago</small>-->
                    <#--</div>-->
                    </div>
                </div>
<#--Exam-info end-->
<#--<div class="row">-->
    <#--<div class="col-12">-->

        <div >
            <div id="dailyRightWrong" style="float: left;"></div>
            <div id="radar"  style="float: right;">
                <canvas id="radar-chart" style="width: 550px; height: 405px"></canvas>
            </div>
        </div>
    <#--</div>-->
<#--</div>-->

<div  style=" width: inherit; max-height: 440px; overflow-x: auto; overflow-y: auto; margin-bottom: 30px; font-size: small">
    <table style="border-collapse: separate; border-spacing: 5px; min-width: 20px;" >
        <thead >
        <tr id="marksTableHead" > </tr>
        </thead>
              <tbody id="marksTableBody"  > </tbody>
     </table>
</div>

<div id="subTopicAndSchoolEngage">

    <#--<div style=" max-height: 440px; max-width: 230px; overflow-y: scroll; float: right; margin-right: 230px;">-->
        <#--<div id="test" class="border" style = " height: 800px; width: 200px; background-color: #ffffff;  "></div>-->
    <#--</div>-->
</div>

<div id="liquidGraph" style="width: fit-content"></div>
<#--<div class="card text-black mb-3" style="width: fit-content; background-color: #ecece9; margin-left: 10px; margin-right: 10px">-->
    <#--<div class="card-header text-white" style="background-color: #7ea121; color: #b4cb79">Progress</div>-->
    <#--<div class="card-body" style="height: fit-content;">-->
            <#--<div  style=" display: table; margin: 0 auto;">-->
                <#--<svg id="fillgauge" width="200" height="200" onclick="gauge1.update(NewValue());"></svg>-->
                <#--<div style="display: table; margin: 0 auto; background-color: white;  width: 85%; margin-top: -124px;">-->
                    <#--<p style="text-align: center; margin-top: 121px;"><b>Overall</b></p>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div id="liquid" class="row" style="margin-top: 10px"> </div>-->
    <#--</div>-->
<#--</div>-->

<#--student result weekly-->
<div class="container-fluid " >
    <div class="row">
        <div class="col-12">
            <div class="card mb-3">
                <div class="card-header card-header-nav">
                    <span id="prev-week" class="btn btn-primary-outline left-nav"><i class="oi oi-chevron-left"></i></span>
                    <span id="next-week" class="btn btn-primary-outline right-nav"><i class="oi oi-chevron-right"></i></span>
                </div>
                <div class="card-body table-responsive">
                    <div class="row">
                        <div class="col-12">
                            <div id="studentResult" class="grid-container grid-container--fit">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/layout/nav/bottom.ftl">

<script src="/js/graph.js"></script>

<style>
    .grid-container {
        display: grid;
        grid-gap: 5px;
    }
    .grid-container--fit {
        grid-template-columns:minmax(170px, 1fr) minmax(170px, 1fr) minmax(170px, 1fr) minmax(170px, 1fr) minmax(170px, 1fr) minmax(170px, 1fr) minmax(170px, 1fr);
    }
    .grid-element {
        background-color: rgba(0,0,0,.03);
        padding: 20px;
        color: black;
        border: 1px solid #212529;
        font-size: 12px;
        font-weight: bold;
        border-radius: 3px;
    }
    .left-nav{
        position: absolute;
        left: -1px;
        top:-1px;
        cursor: pointer;
        border-radius: 3px 0px 0px 0px;
    }
    .right-nav{
        position: absolute;
        right: -1px;
        top: -1px;
        cursor: pointer;
        border-radius: 0px 3px 0px 0px;
    }
    .card-header-nav{
        padding: 20px;
    }
    .btn-primary-outline {
        padding: 8px 20px;
        border: 1px solid rgba(0,0,0,.125);
        background-color: rgba(0,0,0,.03);
        font-weight: 400;
    }

    /*responsive design start*/

    /*@media screen and (max-width:360px){*/
        /*#studentDailyRightWrong{*/

            /*width: 337px;*/
        /*}*/
    /*}*/

    /*responsive design end*/

</style>
<#--<script src="/js/student-result.js"></script>-->

<#--end student result weekly-->
