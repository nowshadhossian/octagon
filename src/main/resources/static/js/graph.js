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
                    position: 'left' // place legend on the right side of chart
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
                    table_row += '<td style="background-color: red; text-align: center">' + topics[topic_names[i]][j] + '</td>';
                else if (topics[topic_names[i]][j] < 80 && topics[topic_names[i]][j] >= 50)
                    table_row += '<td style="background-color: yellow; text-align: center">' + topics[topic_names[i]][j] + '</td>';
                else if (topics[topic_names[i]][j] <= 100 && topics[topic_names[i]][j] >= 80)
                    table_row += '<td style="background-color: green; text-align: center">' + topics[topic_names[i]][j] + '</td>';
                else
                    table_row += '<td style="background-color: white; text-align: center">' + topics[topic_names[i]][j] + '</td>';
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
                // labels: ["Improvement", "Speed", "Accuracy", "Effort", "Consistency"],
                datasets: [
                    // {
                    //     label: "total",
                    //     fill: true,
                    //     backgroundColor: "rgba(179,181,198,0.2)",
                    //     borderColor: "rgba(179,181,198,1)",
                    //     pointBorderColor: "#fff",
                    //     pointBackgroundColor: "rgba(179,181,198,1)",
                    //     data: [8.77,55.61,21.69,6.62,6.82]
                    // },
                    {
                        label: "monthly",
                        fill: true,
                        backgroundColor: "rgba(255,99,132,0.2)",
                        borderColor: "rgba(255,99,132,1)",
                        pointBorderColor: "#fff",
                        pointBackgroundColor: "rgba(255,99,132,1)",
                        pointBorderColor: "#fff",
                        data: Object.values(json),
                        // data: [8.77,4.61,21.69,6.62,6.82]
                    }
                    // {
                    //     label: "weekly",
                    //     fill: true,
                    //     backgroundColor: "rgba(25,99,12,0.2)",
                    //     borderColor: "rgba(55,99,132,199)",
                    //     pointBorderColor: "#cff",
                    //     pointBackgroundColor: "rgba(285,99,232,1)",
                    //     pointBorderColor: "#cff",
                    //     data: [2.48,54.16,-55.61,8.06,48.45]
                    // }
                ]
            },
            options: {
                responsive: false,
                title: {
                    display: true,
                    text: 'radar/spider testing'
                }
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
            red: 'rgb(255, 0, 0)',
            orange: 'rgb(255, 159, 64)',
            yellow: 'rgb(255, 255, 0)',
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
        $('#test').append(' <div style = " height: 40px; width: 200px; background-color: #0c5460;">'+
            '<h6 style="text-align: center">'+'School Engagement Overview'+'</h6>'+
            ' </div><div>'+
            '<div  style=" padding-left: 46px; "><p><span id="phy" style = "color: green; font-size: 50px; text-align: center;">'+Object.values(json)[0]+'</span></br><span     text-align: center;> '+ Object.keys(json)[0]+'</span></P></div>'+
            '<div   style=" padding-left: 46px; "><p><span id ="eng" style = "color: yellow; font-size: 50px;     text-align: center;"> '+Object.values(json)[1]+'</span></br><span    text-align: center;>'+ Object.keys(json)[1]+'</span></P></div>'+
            '<div   style=" padding-left: 46px; "><p><span id="math" style = "color: red; font-size: 50px;    text-align: center;"> '+Object.values(json)[2]+'</span> </br> <span    text-align: center;>'+Object.keys(json)[2]+'</span></P></div>'+
            '<div   style=" padding-left: 46px; "><p><span id="math" style = "color: blue; font-size: 50px;    text-align: center;"> '+Object.values(json)[3]+'</span> </br> <span     text-align: center;>'+Object.keys(json)[3]+'</span>></P></div>'+
            '<div   style=" padding-left: 46px; "><p> <span id="total" style = "color: grey; font-size: 50px;    text-align: center;">'+(Object.values(json)[0]+Object.values(json)[1]+Object.values(json)[2]+Object.values(json)[3])+'</span> </br><span    text-align: center;>total</span></P></div></div>>');

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

        // for (var i=0; i < topicSize; i++){
        //
        //     $('#liquid').append(' <div style="float: left;">\n' +
        //         '        <svg id="fillgauge'+i+'" width="300px" height="180px" onclick="gauge2.update(NewValue());"></svg>\n' +
        //         '        <p  <!-- style="margin-left: 610px; float: left "-->>'+Object.keys(json)[i]+' </p>\n' +
        //         '    </div>');
        //    var gauge2 = loadLiquidFillGauge("fillgauge"+i, Object.values(json)[i]);
        //
        // }

        for (var i=0; i < topicSize; i++){

            $('#liquid').append('\n' +
                '        <svg id="fillgauge'+i+'" width="300px" height="180px" onclick="gauge2.update(NewValue());"></svg>\n' +
                '        <p  <!-- style="margin-left: 610px; float: left "-->>'+Object.keys(json)[i]+' </p>\n');
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
