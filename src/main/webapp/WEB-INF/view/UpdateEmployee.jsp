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
<title>Actualizar Empleado</title>
	<%@include file="/templates/header.html" %>
    <script type="text/javascript">var file="", boolIMG=false;</script>
</head>
<body>
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



	
	<h2 class="text-center">Actualizar Empleado</h2>
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
		<button class="btn btn-warning">Actualizar</button>
	</div>

	
	
	
	
	<%@include file="/templates/footer.jsp" %>
	<%@include file="/templates/scripts.html" %>
</body>
</html>