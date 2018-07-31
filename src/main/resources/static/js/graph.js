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
            'Biology' : [],
            'Chemistry' : [],
            'Math' : [],
            'English': [],
            'Physics' : []
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
            $('#marksTableHead').append('<td>' + new Date(dates[i]) + '</td>');
        }

        var marks_table_body = $('#marksTableBody');
        for (var i = 0; i < topic_names.length; i++)
        {
            var table_row = '<tr>';
            table_row += '<td>' + topic_names[i] + '</td>';
            for (var j=0; j < dates.length; j++)
            {
                console.log(topics[topic_names[i]][j]);
                //table_row += '<td style="background-color: blue">' + topics[topic_names[i]][j] + '</td>';
                if (topics[topic_names[i]][j] < 50 && topics[topic_names[i]][j] >= 0)
                    table_row += '<td style="background-color: red">' + topics[topic_names[i]][j] + '</td>';
                else if (topics[topic_names[i]][j] < 80 && topics[topic_names[i]][j] >= 50)
                    table_row += '<td style="background-color: yellow">' + topics[topic_names[i]][j] + '</td>';
                else if (topics[topic_names[i]][j] <= 100 && topics[topic_names[i]][j] >= 80)
                    table_row += '<td style="background-color: green">' + topics[topic_names[i]][j] + '</td>';
                else
                    table_row += '<td style="background-color: navajowhite">' + topics[topic_names[i]][j] + '</td>';
            }
            table_row += '</tr>';
            $('#marksTableBody').append(table_row);
        }


    });
});