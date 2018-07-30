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
                        <a class="filter-item btn <#if selectedFilter?? && selectedFilter=="All">btn-dark<#else>btn-dark-light</#if>" href="/student/dashboard/question-checklist/?_subject=All">All</a>
                        <#list subjects as subject>
                            <a class="filter-item btn <#if selectedFilter?? && selectedFilter==subject.name>btn-dark<#else>btn-dark-light</#if>" href="/student/dashboard/question-checklist/?_subject=${subject.name}">${subject.name}</a>
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
                            <#list 2004..(.now?string('yyyy')?number) as yr>
                                <div class="box box-year">${yr}</div>
                                <#assign index=0>
                                <#list 1..100 as i>
                                    <#if studentAnswers[index]?? && yr==studentAnswers[index].question.year && i==studentAnswers[index].question.questionNo>
                                        <#if studentAnswers[index].gotCorrect==true>
                                            <div class="box correct-answer"><a href="#"></a></div>
                                        <#else >
                                            <div class="box wrong-answer"><a href="#"></a></div>
                                        </#if>
                                        <#assign index=index+1>
                                    <#else>
                                        <div class="box"><a href="#"></a></div>
                                    </#if>

                                </#list>
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
        grid-template-columns: auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto auto;
        grid-gap: 5px;
        background-color: #fff;
        color: #444;
    }
    .box {
        padding:10px;
        background-color: #dddfe2;
        border: 1px solid #212529;
        color: #fff;
        border-radius: 5px;
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

    @media (min-width: 768px){
        .filter-menu{
            float: right;
            margin-bottom: 10px;
            margin-right: 2px;
        }
    }
</style>
<script>
    // $(function () {
    //     $(".filter-item").click(function () {
    //         $(".filter-item").removeClass("btn-dark");
    //         $(".filter-item").removeClass("btn-dark-light");
    //         this.addClass("btn-dark");
    //     })
    // });
</script>
<#include "/layout/nav/bottom.ftl">