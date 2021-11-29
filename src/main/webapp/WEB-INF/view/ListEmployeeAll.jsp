<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page session="true"  %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<html lang="es">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Registro de empleados - QRAssistance</title>
    <%@include file="/templates/header.html" %>
    <style type="text/css">
        .btnupdateDataProducts {
            display: flex;
            background-color: white;
            color: black;
            border-color: white;
            position: fixed;
            right: 0px;
            top: 5rem;
            padding: 10px;
            font-size: 16pt;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
            border-top-left-radius: 20px;
            border-bottom-left-radius: 20px;
            box-shadow: gray 4px 3px;
            outline: none;
        }
    </style>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
</head>
<% 
try{
	switch(crg){
	case "C01": %><%@include file="/templates/navBarAdmin.jsp" %><%break;
	case "C02": %><%@include file="/templates/navBar.jsp" %><%break;
	case "C03": %><%@include file="/templates/navBarGeren.jsp" %><%break;
	default: break;}
}catch(Exception ex){
	request.getSession().invalidate();
	response.sendRedirect("index.jsp");
}
%>
	<div class="col-12">
		<h2 class="text-center pb-3 font-monospace">Registro total de empleados</h2>
	</div>
    <div class="d-flex justify-content-center">
    	<div class="w-100">
    		<div class="table-responsive">
				<table class="table">
					<thead class="bg-success table-bordered border-success">
						<tr class="text-center text-white">
							<th>Codigo empleado </th>
							<th>Nombres y apellidos</th>
							<th>Correo</th>
							<th>Telefono</th>
							<th>Cargo</th>
							<th>Supervisor</th>
							<th>Area</th>
							<th>Foto</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody id="tableAssistance">
						<c:forEach items="${listaEmployee }" var="item">
							<tr class="text-center">
								<td>${item.cod_empleado }</td>
								<td>${item.ape1_empleado } ${item.ape2_empleado } ${item.nom_empleado }</td>
								<td>${item.correo_empleado }</td>
								<td>${item.tlf_empleado }</td>
								<td>${item.cargo.nombre }</td>
								<td>${item.empleado.ape1_empleado } ${item.empleado.ape2_empleado } ${item.empleado.nom_empleado }</td>
								<td>${item.area.nom_area }</td>
								<td><img src="${item.foto_empleado }" height="70" width="100%"></td>
								<td class="btn-group-vertical text-center">
									<a href="EmployeeUpdate?cod=${item.cod_empleado }" class="btn btn-warning">Actualizar</a>
									<a href="DeleteEmployee?cod=${item.cod_empleado }" class="btn btn-danger">Eliminar</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>
    	</div>
    </div>
    
    
    
<div class="modal" tabindex="-1" id="CredentialsModal">
    <div class="modal-dialog modal-sm">
		<div clas="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Descarga de Credenciales</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="d-none" id="download_credentials">
					<h5 class="mt-2">
						<a href="http://localhost:8080/QRAssistance/pdfs/main.pdf" class="text-success" download="main.pdf">Descargar Credenciales</a>
					</h5>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary m-2" data-bs-dismiss="modal">Cerrar</button>
			</div>
		</div>
    </div>
</div>
    
    
	<button class="d-flex d-inline align-items-center btnupdateDataProducts" onclick="window.location.href = 'http://localhost:8080/QRAssistance/EmployeeListAll';">
	    <i class="material-icons">update</i>&#160; Actualizar
	</button>
	

    <p id="status" class="d-none">${status}</p>
    
<%@include file="/templates/footer.jsp" %>
<%@include file="/templates/scripts.html" %>

    
    
</body>
</html>