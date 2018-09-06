//Student daily right/wrong
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/studentDailyRightWrong/data"
    }).done(function( json ) {
        var ctx = document.getElementById("studentDailyRightWrong").getContext('2d');
        var chart = new Chart(ctx, {
            type: 'bar',
            data: {

                labels: json.map(function(el) {
                    return el.date
                }),
                // create 12 datasets, since we have 12 items
                // data[0] = labels[0] (data for first bar - 'Standing costs') | data[1] = labels[1] (data for second bar - 'Running costs')
                // put 0, if there is no data for the particular bar
                datasets: [{
                    label: 'Correct',
                    data: json.map(function(el) {
                        return el.correct
                    }),
                    backgroundColor: 'aqua'
                }, {
                    label: 'Wrong',
                    data: json.map(function(el) {
                        return el.wrong
                    }),
                    backgroundColor: '#FFB6C1'
                }
                ]
            },
            options: {
                responsive: false,
                legend: {
                    position: 'top' // place legend on the right side of chart
                },
                scales: {
                    xAxes: [{
                        stacked: true // this should be set to make the bars stacked
                    }],
                    yAxes: [{
                        stacked: true, // this also..
                        ticks: {
                            max: 100
                        }
                    }]
                }
            }
        });

    });
});


$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/subtopic/daily/progress/data"
    }).done(function( json ) {
        var topics = {
            'Gen-Forces' : [],
            'Gen-Motion' : [],
            'Ele-Current' : [],
            'Ele-Potential Difference': [],
            'The-Pressure Changes' : [],
            'Wav-Wave Properties' : [],
            'Gen-Energy' : [],
            'Gen-Work' : [],
            'The-Radiation' : [],
            'Wav-Sound' : [],
            'Ato-Half Life' : [],
            'Ato-Decay' : [],
            'Ato-Isotopes' : [],
            'Ato-Emission' : [],
            'The-Convection' : [],
            'Gen-Length' : [],
            'Gen-Acceleration' : [],
            'The-Evaporation' : [],
            'Gen-Pressure' : [],
        };
        var dates = Object.keys(json);
        var daywiseTopic = Object.values(json);
        var topic_names = Object.keys(topics);
        for (var i = 0; i < daywiseTopic.length; i++)
        {
            for (var j = 0; j < topic_names.length; j++)
            {
                topics[topic_names[j]].push(typeof daywiseTopic[i][topic_names[j]] === 'undefined' ? '-' : daywiseTopic[i][topic_names[j]]);
            }
        }

        $('#marksTableHead').append('<td></td>');
        for (var i=0; i < dates.length; i++)
        {
            $('#marksTableHead').append('<td style="max-width: 30px;">' + new Date(dates[i]).toDateString().fixed() + '</td>');
        }

        var marks_table_body = $('#marksTableBody');
        for (var i = 0; i < topic_names.length; i++)
        {
            var table_row = '<tr>';
            table_row += '<td style="min-width: 170px; text-align: right">' + topic_names[i] + '</td>';
            for (var j=0; j < dates.length; j++)
            {
                if (topics[topic_names[i]][j] < 50 && topics[topic_names[i]][j] >= 0)
                    table_row += '<td style="background-color: #c30101; text-align: center">' + topics[topic_names[i]][j] + '</td>';
                else if (topics[topic_names[i]][j] < 80 && topics[topic_names[i]][j] >= 50)
                    table_row += '<td style="background-color: #fbbf2d; text-align: center">' + topics[topic_names[i]][j] + '</td>';
                else if (topics[topic_names[i]][j] <= 100 && topics[topic_names[i]][j] >= 80)
                    table_row += '<td style="background-color: #45b52f; text-align: center">' + topics[topic_names[i]][j] + '</td>';
                else
                    table_row += '<td style="background-color: #ffffff; text-align: center">' + topics[topic_names[i]][j] + '</td>';
            }
            table_row += '</tr>';
            $('#marksTableBody').append(table_row);
        }


    });
});

//radar graph

$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/radar/data"
    }).done(function( json ) {
        new Chart(document.getElementById("radar-chart"), {
            type: 'radar',
            data: {
                labels: Object.keys(json),
                 datasets: [

                    {
                        label: "monthly",
                        fill: true,
                        backgroundColor: "rgba(255,99,132,0.2)",
                        borderColor: "rgba(255,99,132,1)",
                        pointBorderColor: "#fff",
                        pointBackgroundColor: "rgba(255,99,132,1)",
                        pointBorderColor: "#fff",
                        data: Object.values(json),
                    }

                ]
            },
            options: {
                legend:{
                    display: false,
                },

                responsive: false,
                // title: {
                //     display: true,
                //     text: 'radar/spider testing'
                // }
            }
        });

    });
});

//topicSubTopicMarks
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/topicSubTopicMarks/data"
    }).done(function( json ) {
        var chartColors = {
            red: 'rgb(205, 9, 30)',
            orange: 'rgb(255, 159, 64)',
            yellow: 'rgb(245, 167, 32)',
            green: 'rgb(0, 128, 0)',
            blue: 'rgb(54, 162, 235)',
            purple: 'rgb(153, 102, 255)',
            grey: 'rgb(231,233,237)',
        };
        var colorList = [];
        // var colorList = [chartColors.red,chartColors.red,chartColors.red,chartColors.red,chartColors.red,chartColors.red,chartColors.red,chartColors.red,chartColors.red,chartColors.red,'#aaaaff','#aaaaff',];
           // colorList.push(chartColors.yellow);


        var topicListKeys = Object.keys(json);
        var topicListValues = Object.values(json);
        for (var i = 0; i < topicListValues.length; i++){
            if (topicListValues[i] < 50){
                colorList.push(chartColors.red);
            }
            else if(topicListValues[i] >= 50 && topicListValues[i] < 80){
                colorList.push(chartColors.yellow);
            }
            else{
                colorList.push(chartColors.green);
            }
        }
        //var keys = Object.keys(json);


        new Chart(horizontalChart, {
            type: 'horizontalBar',
            data: {
                // labels: ["phy-wave", "phy-magnitude", "phy-speed", "Eng-writing", "Eng-reading","Eng-spoking", "Eng-listening", chartColors.red, "Math-geometry", "Math-Inegration","Math-diff", "Math-Matrix", "Chem-reaction", "Chem-polymer", "Chem-isomerism"],
                labels: topicListKeys,
                datasets: [ {
                    label: "total",
                    fill: true,
                    //backgroundColor: "rgba(1,181,10,0.2)",
                    backgroundColor: colorList,
                    borderColor: "rgba(179,181,198,1)",
                    pointBorderColor: "#4330ff",
                    pointBackgroundColor: "rgba(179,181,198,1)",
                    // data: [8,55,21,6,6,8 ,55 ,21 ,6 ,6 ,8 ,55 ,21 ,6 ,6 ]
                    data: topicListValues,
                }],

            },
            options: {

                responsive: false,
                legend: {
                    display: false,
                    labels: {
                        padding: 20,

                    }
                },
                scales: {
                    xAxes: [{
                        ticks: {

                            beginAtZero: true,
                        },
                        gridLines: {
                            display: true,
                            lineWidth: 1,
                            //drawTicks: false,
                            //drawBorder: false,
                            zeroLineWidth: 1,


                        },

                    }],
                    yAxes: [{
                        barPercentage: .9, // I've also tried all from [0.90, 1]
                        categoryPercentage: .9, // I've also tried all from [0.90, 1]
                        ticks: {
                            padding: 25,

                        },
                    }]
                },
            },
        });


    });
});

// side bar
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/topicWiseMarks/data"
    }).done(function( json ) {
        $('#test').append(' <div style = " height: 40px; width: 200px; background-color: #4f616d;">'+
            '<h6 style="text-align: center; color: white">'+'School Engagement Overview'+'</h6>'+
            ' </div><div>'+
            '<div  style=" padding-left: 46px; "><p><span id="phy" style = "color: #407707; font-size: 50px; text-align: center;">'+Object.values(json)[0]+'</span></br><span     text-align: center;> '+ Object.keys(json)[0]+'</span></P></div>'+
            '<div   style=" padding-left: 46px; "><p><span id ="eng" style = "color: #f6a92f; font-size: 50px;     text-align: center;"> '+Object.values(json)[1]+'</span></br><span    text-align: center;>'+ Object.keys(json)[1]+'</span></P></div>'+
            '<div   style=" padding-left: 46px; "><p><span id="math" style = "color: #cd091e; font-size: 50px;    text-align: center;"> '+Object.values(json)[2]+'</span> </br> <span    text-align: center;>'+Object.keys(json)[2]+'</span></P></div>'+
            '<div   style=" padding-left: 46px; "><p><span id="math" style = "color: blue; font-size: 50px;    text-align: center;"> '+Object.values(json)[3]+'</span> </br> <span     text-align: center;>'+Object.keys(json)[3]+'</span>></P></div>'+
            '<div   style=" padding-left: 46px; "><p> <span id="total" style = "color: #494949; font-size: 50px;    text-align: center;">'+(Object.values(json)[0]+Object.values(json)[1]+Object.values(json)[2]+Object.values(json)[3])+'</span> </br><span    text-align: center;>total</span></P></div></div>');

    });
});



//liquid js for topic marks
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/topicWiseMarks/data"
    }).done(function( json ) {


        var topicSize = Object.keys(json).length;
        var totalMarks = 0;
        for (var j=0; j<topicSize; j++){
            totalMarks += Object.values(json)[j];
        }
        var gauge = loadLiquidFillGauge("fillgauge", totalMarks);

        for (var i=0; i < topicSize; i++){

            $('#liquid').append(
                '<div class="col-md-2">'+
                    '<div style=" display: table; margin: 0 auto;">'+
                        '<svg id="fillgauge'+i+'" width="120px" height="120px" onclick="gauge2.update(NewValue());"></svg>' +
                        '<div style="display: table; margin: 0 auto; background-color: white;  width: 100px; margin-top: -40px;">' +
                            '<p style="text-align: center; margin-top: 35px; color: #6ec1ef">'+Object.keys(json)[i]+'</p>' +
                        '</div>' +
                    '</div>'+
                '</div>');
            var gauge2 = loadLiquidFillGauge("fillgauge"+i, Object.values(json)[i]);

        }

        function NewValue(){
            if(Math.random() > .5){
                return Math.round(Math.random()*100);
            } else {
                return (Math.random()*100).toFixed(1);
            }
        }

    });
});


// start student result weekly
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
//end student result weekly