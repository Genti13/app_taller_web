<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <link href="css/registro.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <div id="loginbox" class="mainbox">
                    <form:form action="registrar-usuario" method="POST" modelAttribute="datosRegistro">
                        <h3 class="form-signin-heading">Completa tus datos</h3>
                        <hr class="colorgraph">

                        <div class="form-group">
                            <label for="email">Email</label>
                            <form:input path="email" type="email" id="email" required="true" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <form:input path="password" type="password" id="password" required="true" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <form:input path="nombre" type="text" id="nombre" required="true" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="apellido">Apellido</label>
                            <form:input path="apellido" type="text" id="apellido" required="true" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="estado">Estado</label>
                        <form:select path="estado" id="estado" class="form-control">
                            <form:option value="" selected="selected">--Seleccionar--</form:option>
                            <form:option value="Cardiaco">Cardiaco</form:option>
                        </form:select>
                        </div>

                        <div class="form-group">
                            <label for="edad">Edad</label>
                            <form:input path="edad" type="text" id="edad" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="altura">Altura</label>
                            <form:input path="altura" type="text" id="altura" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="peso">Peso</label>
                            <form:input path="peso" type="text" id="peso" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="genero">Género</label>
                            <form:select path="genero" type="text" id="genero" class="form-control">
                                <form:option value="" selected="selected">--Seleccionar--</form:option>
                                <form:option value="Masculino">Masculino</form:option>
                                <form:option value="Masculino">Femenino</form:option>
                            </form:select>
                        </div>

                        <div class="form-group">
                            <label for="objetivo">Objetivo</label>
                            <form:select path="objetivo" type="text" id="objetivo" class="form-control">
                                <form:option value="" selected="selected">--Seleccionar--</form:option>
                                <form:option value="0">Gestión</form:option>
                                <form:option value="1">Pérdida de Peso</form:option>
                                <form:option value="2">Ganancia de Peso</form:option>
                            </form:select>
                        </div>

                        <button id="btn-registrarme" class="btn btn-lg btn-success btn-block" type="submit">Registrarme</button>
                    </form:form>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">${error}</div>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6">

            </div>
        </div>
    </div>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
