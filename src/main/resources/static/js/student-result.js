var weekOffset=0; // week offset earlier from current week and it's global
$(document).ready(function(){
    console.log(weekOffset);
    getResult();
});

    $("#prev-week").click(function () {
        weekOffset++; // value indicates earlier from current
        console.log(weekOffset);
        getResult();
    });
    $("#next-week").click(function () {
        if (weekOffset>0){
            weekOffset--; // value indicates earlier from current
            console.log(weekOffset);
            getResult();
        }
    });
function getResult() {
    $.ajax({
        method: "GET",
        url: "/student/dashboard/student-weekly-result?weekOffset="+weekOffset
    }).done(function( json ) {
        showResult(json);
    });
}

function showResult(json) {
    var date = new Date();
    date.setDate(date.getDate()-(7+7*weekOffset));
    var resultElement="";
    for (var i=0;i<7;i++){
        date.setDate(date.getDate()+1);
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