<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page session="true" %>
<%
String crg="";
	HttpSession misession= (HttpSession) request.getSession();
	if(misession.getAttribute("Cargo")==null){
		response.sendRedirect("index.jsp");
	}else{
		crg=misession.getAttribute("Cargo").toString();
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registrar Empleado</title>
	<!-- esto sirve para cargar los stilos de la pagina -->
    <%@include file="/templates/header.html" %>
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
</head>
<body>

	<!-- esto sirve para cargar el strucctura de la barra de menu segun el cago del usuario logueado -->
	<!-- como auún no estac onectado a la BD tienes que cambiar en el IntranetServlet el Codigo del cargo -->
	<!-- respectivamente para el uso de los templates del menu -->
	<!-- segun el intranetServlet esta configurado para el perfil del ADMIN -->
	<% 
	try{
		switch(crg){
		case "C01": %><%@include file="/templates/navBarAdmin.jsp" %><%break; // empleado administrador del sistema
		case "C02": %><%@include file="/templates/navBar.jsp" %><%break; // empleado normal
		case "C03": %><%@include file="/templates/navBarGeren.jsp" %><%break; // empleado gerencial
		default: break;}
	}catch(Exception ex){
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
	%>



	
	<h2 class="text-center">Registrar Empleado</h2>
	<div class="row">
		<div class="col align-self-start">
			<div class="col-4 mt-2 ">
				<label>Nombre</label>
				<input class="form-control" placeholder="Ingrese nombre"/>
			</div>
			<div class="col-6 mt-2">
				<label>Apellidos</label>
				<input class="form-control" placeholder="Ingrese apellidos"/>
			</div>
			<div class="col-8 mt-2">
				<label>Correo</label>
				<input class="form-control" placeholder="Ingrese correo electronico"/>
			</div>
			<div class="col-4 mt-2">
				<label>	DNI</label>
				<input class="form-control" placeholder="Ingrese DNI"/>
			</div>
			<div class="col-6 mt-2">
				<label>Telefono</label>
				<input class="form-control" placeholder="Ingrese telefono"/>
			</div>
			<div class="col-8 mt-2">
				<label>Cargo</label>
				<select class="form-control"></select>
			</div>
		</div>
		<div class="col align-self-end">
			<img alt="" src="">
			<div class="col text-center">
				<button class="btn btn-primary">Subir imagen</button>
			</div>
		</div>
	</div>
	<div class="col mt-4  text-center">
		<button class="btn btn-success">Registrar</button>
	</div>

	
	
	
	
	<%@include file="/templates/footer.jsp" %>
	<%@include file="/templates/scripts.html" %>
</body>
</html>