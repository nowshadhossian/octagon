<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card mb-3">
                <div class="card-header card-header-nav">
                    <span id="prev-week" class="btn btn-primary-outline left-nav">prev week</span>
                    <span id="next-week" class="btn btn-primary-outline right-nav">next week</span>
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
<script src="/js/student-result.js"></script>
