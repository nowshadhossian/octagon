<#-- @ftlvariable name="oldestQuestionYear" type="java.lang.Integer" -->
<#-- @ftlvariable name="questionKeys" type="java.util.List<com.kids.crm.pojo.QuestionKey>" -->
<#-- @ftlvariable name="QUESTION_KEY" type="com.kids.crm.pojo.QuestionKey" -->
<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="col-12">
    <h1>Question Checklist</h1>
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">
            <div class="row">
                <div class="col-12">
                    <div class="filter-menu">
                        <a class="filter-item btn <#if selectedFilter?? && selectedFilter=="All">btn-dark<#else>btn-dark-light</#if>" href="/student/dashboard/question-checklist/?_topic=All">All</a>
                        <#list topics as topic>
                            <a class="filter-item btn <#if selectedFilter?? && selectedFilter==topic.id?c>btn-dark<#else>btn-dark-light</#if>" href="/student/dashboard/question-checklist/?_topic=${topic.id}">${topic.name}</a>
                        </#list>
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fa fa-table"></i>
                </div>
                <div class="card-body table-responsive">

                    <div class="row">
                        <div class="wrapper">
                            <div class="box box-year header-year"></div>
                            <#list 1..100 as i>
                                <#if i<10 >
                                    <div class="box box-question-No-2">
                                        &nbsp;&nbsp;${i}&nbsp;&nbsp;
                                    </div>
                                <#elseif i<100>
                                    <div class="box box-question-No-1">
                                        &nbsp;${i}&nbsp;
                                    </div>
                                <#else>
                                    <div class="box box-question-No-1">
                                        ${i}
                                    </div>
                                </#if>
                            </#list>
                            <div class="box box-year header-year"></div>
                            <#assign index=0>
                            <#list oldestQuestionYear..(.now?string('yyyy')?number) as yr>
                                <div class="box box-year">${yr}</div>
                                <#list 1..100 as i>
                                    <#assign questionNo = i/>
                                    <#assign examUrl = "/student/dashboard/question-checklist/start/answer/questionNo/${questionNo}/year/${yr}"/>
                                    <#if studentAnswers[index]?? && yr==studentAnswers[index].question.year && i==studentAnswers[index].question.questionNo>
                                        <#if studentAnswers[index].gotCorrect==true>
                                            <a href="${examUrl!"#"}"><div class="box correct-answer"></div></a>
                                        <#else >
                                            <a href="${examUrl!"#"}"><div class="box wrong-answer"></div></a>
                                        </#if>
                                        <#assign index=index+1>
                                    <#else>
                                        <a href="${examUrl!"#"}"><div class="box <#if questionKeys?? && QUESTION_KEY.get(yr, i)??  && questionKeys?seq_contains(QUESTION_KEY.get(yr, i))>similar-topic</#if>"></div></a>
                                    </#if>
                                </#list>
                                 <#assign questionNo = 0/>
                                 <#assign examUrl = "/student/dashboard/question-checklist/start/answer/questionNo/${questionNo}/year/${yr}"/>
                                 <a href="${examUrl!"#"}"><div class="box box-year" style="font-size: 16px;">Give</div></a>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-2">

        </div>
    </div>
</div>

<style>
    .wrapper {
        display: grid;
        grid-template-columns: auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto;
        grid-gap: 0px;
        background-color: #fff;
        color: #444;
    }
    .box {
        padding:13px;
        background-color: #dddfe2;
        border: 1px solid #212529;
        color: #fff;
        border-radius: 0px;
    }
    .box-year{
        padding: 1px;
        color: black;
    }
    .header-year{
        border: none;
        background-color: none;
    }
    .box-question-No-1{
        color: black;
        padding: 1px;
    }
    .box-question-No-2{
        color: black;
        padding: 2px;
    }
    .correct-answer{
        background-color: green;
    }
    .wrong-answer{
        background-color: red;
    }
    .filter-menu{
        margin-bottom: 10px;
        margin-left: 2px;
    }
    .similar-topic{
        background-color: black;
    }

    @media (min-width: 768px){
        .filter-menu{
            float: right;
            margin-bottom: 10px;
            margin-right: 2px;
        }
    }
</style>

<#include "/layout/nav/bottom.ftl">
<script>
    // $(function () {
    //     $(".filter-item").click(function () {
    //         $(".filter-item").removeClass("btn-dark");
    //         $(".filter-item").removeClass("btn-dark-light");
    //         this.addClass("btn-dark");
    //     })
    // });

    // switch subject show for more than one subject
    $(document).ready(function(){
        $.ajax({
            method: "GET",
            url: "/student/dashboard/graph/totalSubject"
        }).done(function( json ) {
            var resultElement = "";
            resultElement += '<a class="nav-link" href="/student/switch-batch">\n' +
                    '                    <i class="fa fa-fw fa-table"></i>\n' +
                    '                    <span class="nav-link-text">Switch Subject</span>\n' +
                    '                 </a>';
            if (json < 2){

            }
            else {
                $('#switchSubject').append(resultElement);
            }

        });
    });
</script>