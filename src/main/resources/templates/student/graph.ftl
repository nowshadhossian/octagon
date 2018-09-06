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

<div  style=" width: 50%; max-height: 440px; overflow-x: scroll; overflow-y: scroll; margin-bottom: 30px">
    <table style="border-collapse: separate; border-spacing: 5px; width: 100%;  " >
        <thead >
        <tr id="marksTableHead" > </tr>
        </thead>
              <tbody id="marksTableBody"  > </tbody>
     </table>
</div>

<div">
    <div class="border" style="height: 440px; width: 750px; background-color: #ffffff; float: left; margin-bottom: 30px;">
        <div style="background-color: #4f616d; width: 750px; height: 40px; text-align: center">
            <h6 style="padding-top: 5px; color: white">Sub Topic Completion</h6>
        </div>
        <div style=" max-height: 400px; max-width: 730px; overflow-y: scroll; float: left;">
            <canvas id="horizontalChart" width="700" height="600" ></canvas>
        </div>
    </div>
    <div style=" max-height: 440px; max-width: 230px; overflow-y: scroll; float: right; margin-right: 230px;">
        <div id="test" class="border" style = " height: 800px; width: 200px; background-color: #ffffff;  "></div>
    </div>
</div>






<div class="card text-black mb-3" style="width: fit-content; background-color: #ecece9;margin-left: 10px; margin-right: 10px">
    <div class="card-header text-white" style="background-color: #7ea121; color: #b4cb79">Progress</div>
    <div class="card-body" style="height: fit-content;">
            <div  style=" display: table; margin: 0 auto;">
                <svg id="fillgauge" width="200" height="200" onclick="gauge1.update(NewValue());"></svg>
                <div style="display: table; margin: 0 auto; background-color: white;  width: 85%; margin-top: -124px;">
                    <p style="text-align: center; margin-top: 121px;"><b>Overall</b></p>
                </div>
            </div>
            <div id="liquid" class="row" style="margin-top: 10px"> </div>
    </div>
</div>



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


<#include "/layout/nav/bottom.ftl">

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
</style>
<#--<script src="/js/student-result.js"></script>-->
<#--end student result weekly-->

