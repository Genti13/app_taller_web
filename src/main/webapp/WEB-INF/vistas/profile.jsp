<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/profile.css">
    <title>Profile</title>
</head>

<body>

    <div class="profile_topBanner px-2 py-4 d-flex">
        <div>
            <img src="https://upload.wikimedia.org/wikipedia/commons/3/32/Star_Wars_-_Darth_Vader.jpg" alt="profilePic"
                class="profile_pic">
        </div>
        <div class="ms-3 mt-3 text-white">
            <div class="d-flex">
                <h2 class="me-2 mb-0">${nombre}</h2>
                <h2 class="me-2 mb-0">${apellido}</h2>
            </div>
            <h4 class="mt-0">CS: ${lastCS}</h4>
            <h5>${birthday}</h5>
            <h5>Descripcion:</h5>
            <div class="profile_description">
                <p class="mb-0">${description}
                </p>
            </div>
        </div>
    </div>

    <div class="profile_body mt-3">
        <div class="row gap-3 justify-content-center">
            <div id="chart_conditionScore" style="width: 600px;height:400px;" class="profile_panels col-auto"></div>
            <div class="col-auto text-component profile_panels" style="width: 600px;height:400px;">
                <h1>Funcionalidad: Dietas de esta semana van aca</h1>
            </div>
            <div class="col-auto text-component profile_panels" style="width: 600px;height:400px;">
                <h1>Funcionalidad: Historial de Dietas Va aca</h1>
            </div>
        </div>
    </div>
</body>

<script src="js/echarts.min.js"></script>
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

    let historic_cs;
    let historic_weeks = [];
    $.ajax({
        url: "/proyecto-limpio-spring/pedir_CS",
        type: "GET",
        success: function (data) {
        console.log(data)

            historic_cs = data;
            
            historic_cs.forEach((week, n_week) => {
                historic_weeks.push('Week ' + n_week);
            })

            generateGraph(historic_cs, historic_weeks);
        },
        error: function () {
            console.log("FALLO");
        }
    });

    function generateGraph(historic_cs, historic_weeks) {
        var myChart = echarts.init(document.getElementById('chart_conditionScore'));
        var option = {
            title: {
                top: 4,
                left: 'center',
                text: 'Historic Condition Score'
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                top: 0,
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: historic_weeks
            },
            yAxis: {
                min: 0,
                max: 100,
                type: 'value'
            },
            visualMap: {
                top: 30,
                left: 'center',
                orient: 'horizontal',
                pieces: [{
                    gt: 0,
                    lte: 25,
                    color: '#FD0100',
                },
                {
                    gt: 25,
                    lte: 50,
                    color: '#FC7D02',
                },
                {
                    gt: 50,
                    lte: 75,
                    color: '#FBDB0F',
                },
                {
                    gt: 75,
                    lte: 100,
                    color: '#93CE07',
                }
                ],
                outOfRange: {
                    color: '#999'
                }
            },
            series: [
                {
                    name: 'Condition Score',
                    type: 'line',
                    stack: 'Total',
                    data: historic_cs
                }
            ]
        };

        myChart.setOption(option);
    }


</script>



</html>