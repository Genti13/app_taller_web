<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	    <!-- Css -->
        <link href="css/login.css" rel="stylesheet">
	</head>
<div class="container">
    <div class="row">
        <div class="col-md-5">
            <!-- Columna de la izquierda con el formulario de inicio de sesión -->
                       <div id="loginbox" class="mainbox">
                           <form:form action="profile" method="POST" modelAttribute="datosLogin">
                               <h3 class="form-signin-heading">Bienvenido a LifeBetter</h3>
                               <hr class="colorgraph">

                               <form:input path="email" id="email" type="email" class="form-control" placeholder="Ingrese su usuario" />
                               <br/>
                               <form:input path="password" type="password" id="password" class="form-control" placeholder="Ingrese la contraseña" />
                               <br/>

                               <button class="btn btn-lg btn-success btn-block" type="Submit">Ingresar</button>
                           </form:form>
                           <br/>

                             <a href="registro-usuario">Registrarse</a>


                           <c:if test="${not empty error}">
                               <h4><span>${error}</span></h4>
                               <br>
                           </c:if>
                           ${msg}
                       </div>
        </div>
        <div class="col-md-6">
         <!-- Columna de la derecha-->
        </div>
    </div>
</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
