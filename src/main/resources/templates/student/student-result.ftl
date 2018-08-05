<#assign title="Dashboard | Octagon">
<#assign navPage="/layout/nav/student-nav.ftl">
<#include "/layout/nav/top.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fa fa-table"></i>
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
        grid-template-columns: minmax(200px, 1fr) minmax(200px, 1fr) minmax(200px, 1fr) minmax(200px, 1fr) minmax(200px, 1fr) minmax(200px, 1fr) minmax(200px, 1fr);
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
</style>
<script>
    $(document).ready(function(){
        var weekOffset=0; // week offset from current week
        getResult(weekOffset);
    });

    function getResult(weekOffset) {
        $.ajax({
            method: "GET",
            url: "/student/dashboard/student-weekly-result?weekOffset="+weekOffset
        }).done(function( json ) {
            showResult(json);
        });
    }

    function showResult(json) {
        var date = new Date();
        date.setDate(date.getDate()-7);
        var resultElement="";
        for (var i=0;i<7;i++){
            date.setDate(date.getDate()+i);
            var monthNo = (date.getMonth() + 1);
            var day = date.getDate();
            var formatedDate = date.getFullYear()+'-'+(monthNo<10 ? '0' : '')+monthNo+'-'+(day<10 ? '0' : '') + day;
            resultElement=resultElement+'<div class="grid-element">';
            resultElement=resultElement+'<h5>';
            resultElement=resultElement+formatedDate;
            resultElement=resultElement+'</h5>';
            if($.isEmptyObject(json[formatedDate])){
                resultElement=resultElement+'<h6>';
                resultElement=resultElement+'Result not available';
                resultElement=resultElement+'</h6>';
            } else {
                $.each(json[formatedDate],function (key,answer) {
                    resultElement=resultElement+'<div>';
                    resultElement=resultElement+key+': '+ answer.correct+"/"+(answer.correct+answer.wrong);
                    resultElement=resultElement+'</div>';
                })
            }
            resultElement=resultElement+'</div>';
        }
        $("#studentResult").html(resultElement);
    }
</script>
