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
		<h2 class="text-center pb-3 font-monospace">Historial de Asistencia</h2>
	</div>
    <div class="d-flex justify-content-center">
    	<div class="w-50">
    		<div class="table-responsive">
				<table class="table text-white">
					<thead class="bg-success">
						<tr class="text-center">
							<th>Ingreso </th>
							<th>Salida</th>
						</tr>
					</thead>
					<tbody id="tableAssistance">
						<c:forEach items="${listaAssitance2 }" var="item">
							<tr class="text-center">
								<td>${item.inicio }</td>
								<td>${item.fin }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>
    	</div>
    </div>
<%@include file="/templates/footer.jsp" %>
<%@include file="/templates/scripts.html" %>

    
</body>
</html>