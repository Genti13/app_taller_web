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
                    <form:form action="act-condiciones" method="POST" modelAttribute="datosRegistro">
                        <h3 class="form-signin-heading">Para brindarte una experiencia personalizada, completa los siguientes campos</h3>
                        <hr class="colorgraph">

                        <div class="form-group">
                            <label for="estado">Estado</label>
                            <form:select path="estado" class="form-control">
                                <form:option value="0">Sin enfermedad</form:option>
                                <form:option value="1">Cardiaco</form:option>
                                <form:option value="2">Diabetico</form:option>
                                <form:option value="3">Hipertenso</form:option>
                                <form:option value="4">Asmatico</form:option>
                                <form:option value="5">Celiaco</form:option>
                            </form:select>
                        </div>

                        <div class="form-group">
                            <label for="objetivo">Objetivo</label>
                            <form:select path="objetivo" class="form-control">
                                 <form:option value="0">Gestion</form:option>
                                 <form:option value="1">Ganancia de peso</form:option>
                                 <form:option value="2">Perdida de peso</form:option>
                            </form:select>
                        </div>

                        <button id="btn-registrarme" class="btn btn-lg btn-success btn-block" type="submit">Actualizar condiciones</button>
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
