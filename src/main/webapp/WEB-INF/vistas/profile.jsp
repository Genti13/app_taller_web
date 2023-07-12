<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
       <link rel="stylesheet" href="css/profile.css">
      <title>Home</title>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-1 sidebar">
                <div class="d-flex flex-column align-items-center my-3">
                           <button href="editar-perfil" class="btn custom-button btn-block text-color mb-4 mt-4" style="height: 50px; width: 100%;"><i class="fas fa-user-edit"></i></button>
                           <button class="btn custom-button btn-block text-color mb-4" style="height: 50px; width: 100%;"><i class="fas fa-lightbulb"></i></button>
                           <button class="btn custom-button btn-block text-color mb-4" style="height: 50px; width: 100%;"><i class="fas fa-sign-out-alt"></i></button>
                </div>
            </div>
            <div class="col-11">
                <div class="profile_topBanner px-2 py-4 d-flex">
                    <div class="ms-3 text-white">
                        <div class="d-flex">
                            <h2 class="me-2 mb-0">Bienvenido, ${nombre}</h2>
                            <h2 class="me-2 mb-0">${apellido}!</h2>
                        </div>
                        <h4 class="mt-0">Puntaje actual: ${lastCS}</h4>
                        <h5>${birthday}</h5>
                    </div>
                    <div class="ms-auto">
                            <img src="img/icono.png" alt="Icono" class="icono-img">
                        </div>

                </div>

                <div class="profile_body mt-3">
                    <div class="row gap-3 justify-content-center">
                        <div id="chart_conditionScore" style="width: 600px; height: 400px;" class="profile_panels col-auto"></div>

                        <div class="col-4 text-component profile_panels" style="height: 400px;">
                            <h4 class="ps-1 pt-1">Tu dieta para esta semana</h4>
                            <div>
                                <h5 class="col-3">Menu</h5>
                                <div class="row">
                                    <h6 class="col-3">Ingredientes</h6>
                                    <h6 class="col-3">Calorias</h6>
                                </div>

                                <div class="row">
                                    <div class="col-3" id="ingrediente1"></div>
                                    <div class="col-3" id="calorias1"></div>
                                </div>

                                <div class="row">
                                    <div class="col-3" id="ingrediente2"></div>
                                    <div class="col-3" id="calorias2"></div>
                                </div>
                            </div>

                            <div>
                                <h5 class="col-3 pt-2">Rutina</h5>
                                <div class="row">
                                    <h6 class="col-3">Ejercicio</h6>
                                    <h6 class="col-3">Duracion</h6>
                                    <h6 class="col-5">Calorias Quemadas</h6>
                                </div>

                                <div class="row">
                                    <div class="col-3" id="ejercicio1"></div>
                                    <div class="col-3" id="duracion1"></div>
                                    <div class="col-5" id="caloriasQ1"></div>
                                </div>

                                <div class="row">
                                    <div class="col-3" id="ejercicio2"></div>
                                    <div class="col-3" id="duracion2"></div>
                                    <div class="col-5" id="caloriasQ2"></div>
                                </div>
                            </div>
                        </div>

                       <div class="col-4 text-component profile_panels" style="height: 400px;">
                            <h4 class="ps-1 pt-1">Explorar ejercicios</h4>
                        </div>
                    </div>
                </div>

                <div class="profile_body mt-3">
                    <div class="row gap-3 justify-content-center">
                        <div class="col-12 text-component profile_panels" style="width: 90%; height: 400px;">
                            <h4 class="ps-1 pt-1">Todas tus dietas</h4>
                            <%--
                            <c:if test="${conditionScore != null}">
                            <ul>
                                <c:forEach var="cs" items="${conditionScore}">
                                    <h1><c:out value="${cs}" /></h1>
                                </c:forEach>
                            </ul>
                            </c:if>
                            --%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/echarts.min.js"></script>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        let historic_cs = [];
        <c:forEach var="punto" items="${conditionScore}">
            historic_cs.unshift('<c:out value="${punto}" />');
        </c:forEach>

        var user = "alanaumente@gmail.com";
        var pass = "1234";

        $.ajax({
            url: "/proyecto-limpio-spring/getAllDietas?user=" + user + "&pass=" + pass,
            method: "GET",
            success: function(response) {
                console.log(response);

                $('#ingrediente1').html(response[0].menus[0].platos[0].ingredientes[0].nombre);
                $('#calorias1').html(response[0].menus[0].platos[0].ingredientes[0].calorias);

                $('#ingrediente2').html(response[0].menus[0].platos[0].ingredientes[1].nombre);
                $('#calorias2').html(response[0].menus[0].platos[0].ingredientes[1].calorias);

                $('#ejercicio1').html(response[0].rutinas[0].ejercicios[0].nombre);
                $('#duracion1').html(response[0].rutinas[0].ejercicios[0].duracion);
                $('#caloriasQ1').html(response[0].rutinas[0].ejercicios[0].valorEnBaseACaloriasQuemadas);

                $('#ejercicio2').html(response[0].rutinas[0].ejercicios[1].nombre);
                $('#duracion2').html(response[0].rutinas[0].ejercicios[1].duracion);
                $('#caloriasQ2').html(response[0].rutinas[0].ejercicios[1].valorEnBaseACaloriasQuemadas);
            },
            error: function(xhr, status, error) {

            }
        });

        let historic_weeks = [];

        historic_cs.forEach((week, n_week) => {
            historic_weeks.push('Week ' + n_week);
        })

        generateGraph(historic_cs, historic_weeks);

        function generateGraph(historic_cs, historic_weeks) {
            var myChart = echarts.init(document.getElementById('chart_conditionScore'));
            var option = {
                title: {
                    top: 4,
                    left: 'center',
                    text: 'Historial de puntaje Semanal'
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
                        data: historic_cs,
                    }
                ]
            };

            myChart.setOption(option);
        }
    </script>
</body>

</html>
