//Student daily right/wrong
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/studentDailyRightWrong/data"
    }).done(function( json ) {
        var totalCorrect = 0;
        var totalwrong = 0;
        for(var i = 0; i < json.length; i++){
            totalCorrect = totalCorrect + Object.values(json)[i].correct;
            totalwrong = totalwrong + Object.values(json)[i].wrong;
        }

        if (totalwrong == 0 && totalCorrect == 0){}
        else {
            var element = "";
            element = element + '<canvas id="studentDailyRightWrong" width="550" height="405"></canvas>';
            $('#dailyRightWrong').append(element);
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

        }
    });
});

 // To do Nowshad Hossain
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/subtopic/daily/progress/data"
    }).done(function( json ) {


            var topics = {

                'Ele-Electric Charge' : [],
                'Ele-Current' : [],
                'Gen-Magnetism' : [],
                'Eng-Grammar' : [],
                'Wav-Refraction' : [],
                'Eng-Verb' : [],
                'Ele-Potential Difference': [],
                'Gen-Motion' : [],
                'The-Melting and Boiling' : [],
                'Wav-Sound' : [],
                'Gen-Motion-acceleration' : [],
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
        if (Object.keys(json).length == 0 ) {

        }
        else {
            var resultElement = "";
            resultElement = resultElement + '    <div class="border" style="height: 440px; width: 750px; background-color: #ffffff; float: left; margin-bottom: 30px;">\n' +
                                            '        <div style="background-color: #4f616d; width: 750px; height: 40px; text-align: center">\n' +
                                            '            <h6 style="padding-top: 5px; color: white">Sub Topic Completion</h6>\n' +
                                            '        </div>\n' +
                                            '        <div style=" max-height: 400px; max-width: 730px; overflow-y: scroll; float: left;">\n' +
                                            '            <canvas id="horizontalChart" width="700" height="600" ></canvas>\n' +
                                            '        </div>\n' +
                                            '    </div>';
            $('#subTopicAndSchoolEngage').append(
                resultElement
            );
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
        }


    });
});

// side bar
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/topicWiseMarks/data"
    }).done(function( json ) {
        if (Object.keys(json).length == 0){}
        else {

            var element = '    <div style=" max-height: 440px; max-width: 230px; overflow-y: scroll; float: right; margin-right: 230px;">\n' +
                '        <div id="test" class="border" style = " height: 800px; width: 200px; background-color: #ffffff;  "></div>\n' +
                '    </div>';

            $('#subTopicAndSchoolEngage').append(
                element
            );
            var totalScores = 0;
            var valuesLength = Object.values(json).length;
            for(var i=0; i < valuesLength; i++){
                totalScores += Object.values(json)[i];
            }

            var resultElement="";
            resultElement =  resultElement + ' <div style = " height: 40px; width: 200px; background-color: #4f616d;">'+
                '<h6 style="text-align: center; color: white">'+'School Engagement Overview'+'</h6>'+
                ' </div>' + '<div>';
            var colorvalue = ["#407707", "#f6a92f", "#407707", "#f6a92f"];
            for(var j=0; j < valuesLength; j++){
                resultElement = resultElement + '<div  style=" padding-left: 46px; "><p><span id="phy" style = "color: '+colorvalue[j]+'; font-size: 50px; text-align: center;">'+Object.values(json)[j]+'</span></br><span     text-align: center;> '+ Object.keys(json)[j]+'</span></P></div>';
            }
            resultElement = resultElement + '<div style=" padding-left: 46px; "><p> <span id="total" style = "color: #494949; font-size: 50px;    text-align: center;">'+totalScores+'</span> </br><span    text-align: center;>total</span></P></div>';
            resultElement = resultElement + '</div>';

            $('#test').append(
                resultElement
            );


            $('#totalScore').append(totalScores);
        }

    });
});

// Days to go
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/daysToGo/data"
    }).done(function( json ) {

        $('#daysToGo').append(json);

    });
});

// averageTimePerQues
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/averageTimePerQues/data"
    }).done(function( json ) {

        $('#averageTimePerQuestion').append(json);

    });
});



//liquid js for topic marks
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/student/dashboard/graph/topicWiseMarks/data"
    }).done(function( json ) {

        if (Object.keys(json).length == 0){
        }
        else{
            var resultElement = "";
            resultElement = resultElement + '<div class="card text-black mb-3" style="width: fit-content; background-color: #ecece9; margin-left: 10px; margin-right: 10px;">\n' +
                '    <div class="card-header text-white" style="background-color: #7ea121; color: #b4cb79">Progress</div>\n' +
                '    <div class="card-body" style="height: fit-content;">\n' +
                '            <div  style=" display: table; margin: 0 auto;">\n' +
                '                <svg id="fillgauge" width="200" height="200" onclick="gauge1.update(NewValue());"></svg>\n' +
                '                <div style="display: table; margin: 0 auto; background-color: white;  width: 85%; margin-top: -124px;">\n' +
                '                    <p style="text-align: center; margin-top: 121px;"><b>Overall</b></p>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div id="liquid" class="row" style="margin-top: 10px"> </div>\n' +
                '    </div>\n' +
                '</div>';
            $('#liquidGraph').append(resultElement);
            var topicSize = Object.keys(json).length;
            var totalMarks = 0;
            for (var j=0; j<topicSize; j++){
                totalMarks += Object.values(json)[j];
            }
            var gauge = loadLiquidFillGauge("fillgauge", totalMarks);
            var columnInSize = "";
            if(topicSize == 2){
                columnInSize = "col-md-6";
            }
            else if (topicSize == 3) {
                columnInSize = "col-md-4";
            }
            else if (topicSize == 4) {
                columnInSize = "col-md-3";
            }
            else if (topicSize == 5) {
                columnInSize = "col-md-4";
            }
            else if (topicSize == 6) {
                columnInSize = "col-md-2";
            }
            else if (topicSize == 7) {
                columnInSize = "col-md-3";
            }
            else if (topicSize > 7) {
                columnInSize = "col-md-2";
            }
            for (var i=0; i < topicSize; i++){

                $('#liquid').append(
                    '<div class='+columnInSize+'>'+
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