<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <script type="text/javascript">var fileEDIT="", boolIMGEDIT=false;</script>
    
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
	<form action="EmployeeUpdatee" method="POST" role="form" enctype="multipart/form-data">
		<div class="row">
		<div class="col align-self-start">
			<div class="col-4 mt-2 ">
				<input class="d-none" name="cod_empleado" value="${e.cod_empleado }" type="text" id="code_employeeE" style="display: none;"/>
				<input class="d-none" name="fecha_registro" value="${e.fecha_registro }" type="text" id="fecha_registroE" style="display: none;"/>
				<label>Nombre</label>
				<input class="form-control" name="nom_empleado" value="${e.nom_empleado }" type="text" placeholder="Ingrese nombre" id="name_employeeE"/>
			</div>
			<div class="col-6 mt-2">
				<label>Apellido Paterno</label>
				<input class="form-control" name="ape1_empleado" value="${e.ape1_empleado }" type="text" placeholder="Ingrese apellido P" id="ape_paternoE"/>
			</div>
			<div class="col-6 mt-2">
				<label>Apellido Materno</label>
				<input class="form-control" name="ape2_empleado" value="${e.ape2_empleado }" type="text" placeholder="Ingrese apellido M" id="ape_maternoE"/>
			</div>
			<div class="col-8 mt-2">
				<label>Correo</label>
				<input class="form-control" name="correo_empleado" value="${e.correo_empleado }" type="email" placeholder="Ingrese correo electronico" id="email_employeeE" readonly="readonly"/>
			</div>
			<div class="col-6 mt-2">
				<label>Telefono</label>
				<input class="form-control" name="tlf_empleado" value="${e.tlf_empleado }" type="text" placeholder="Ingrese telefono" id="phone_employeeE"/>
			</div>
			<div class="col-4 mt-2">
				<label>	DNI</label>
				<input class="form-control" name="dni" value="" type="text" placeholder="Ingrese DNI" id="codedni_employeeE"/>
			</div>
			<div class="col-8 mt-2">
				<label>Area</label>
				<form:select path="listaareas" value="${e.area.nom_area }" id="area_employeeE" class="form-control" name="area">
					<form:option value="none" label="seleccione Area"></form:option>
					<form:options items="${listaareas }" itemLabel="nom_area" itemValue="cod_area" />
				</form:select>
			</div>
		</div>
		<div class="col align-self-end">
			
			<div class="col-8 mt-2">
				<label>Cargo</label>
				<form:select path="listacargos" value="${e.cargo.nombre }" id="cargo_employeeE" class="form-control" name="cargo">
					<form:option value="none" label="seleccione cargo"></form:option>
					<form:options items="${listacargos }" itemLabel="nombre" itemValue="codCargo" />
				</form:select>
			</div>
			
			<div class="col-8 mt-2">
				<label>Supervisor</label>
				<form:select path="listasupervisor"  id="super_employeeE" class="form-control">
					<form:option value="none" label="seleccione Supervisor"></form:option>
					<form:options items="${listasupervisor }" itemLabel="ape1_empleado" itemValue="cod_empleado" />
				</form:select>
			</div>
			<input name="foto_empleado" value="" class="d-none" id="foto_empleado">
			<label for="imgproducfile" class="form-label">Foto del Empleado:</label>
			<input type="file" id="imgproducfile" value="${e.foto_empleado }" accept="image/png, image/jpeg, image/gif" hidden="" class="d-none" style="pointer-events: none;">
			<div class="row">
				<div class="col-6" id="divimgpreview">
					<img src="images/UploadLogo.jpg" class="img-fluid" name="imgPreview" id="imgPreview" style="width: 100%; max-height: 250px; border: 1px solid black;" >
				</div>
				<div class="col-6 d-flex align-items-center">
					<button type="button" class="btn btn-info d-none" id="saveimgproduct">Subir Imagen</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col mt-4  text-center">
		<button class="btn btn-success" onclick="sendEmployeeUpdate();">Actualizar</button>
	</div>
	</form>

	
	
	<%@include file="/templates/footer.jsp" %>
	<%@include file="/templates/scripts.html" %>
	
	<script>
		$("img[name=imgPreview]").click(function () {
			$('#imgproducfile').click();
			fileEDIT="imgproducfile";
			$("#saveimgproduct").addClass("d-none");
		});
		$("#saveimgproduct").click(function(){
			UploadImage();
		});
		function readURL(input) {
		  if (input.files && input.files[0]) {
		    var readerX = new FileReader();
		    readerX.onload = function(e) {
		      // Asignamos el atributo src a la tag de imagen
		      $('#imgPreview').attr('src', e.target.result);
		    }
		    readerX.readAsDataURL(input.files[0]);
		  }
		}
		// El listener va asignado al input
		$("#imgproducfile").change(function() {
		  readURL(this);
			$("#saveimgproduct").removeClass("d-none");
		});
		
		function UploadImage(){
			if (fileEDIT != '') {
		        var filexX = document.getElementById('imgproducfile');		
				var formX = new FormData();
				if (filexX.files.length > 0) {
		            formX.append("image", filexX.files[0])

		            fileEDIT = "";

					var settings = {
					  "url": "https://api.imgbb.com/1/upload?key=ce434f38c832ffc0a2bf395cb9c67be5",
					  "method": "POST",
					  "timeout": 0,
					  "processData": false,
					  "mimeType": "multipart/form-data",
					  "contentType": false,
					  "data": formX
					};
								
					$.ajax(settings).done(function (response) {
					  var jxX = JSON.parse(response);
					  URLImage=jxX.data.url;
					  $("#saveimgproduct").addClass("d-none");
					  boolIMGEDIT= true;
					  $('#imgPreview').attr('src', URLImage);
					  $("#foto_empleado").val(URLImage);
					  
					});
		        }else {
		        	swal({
	    				title: "¡ Error !",
	    				text: "Debes seleccionar una imagen o archivo para mostrar en la web",
	    				icon: "error",
	    				button: "continuar",
	    				closeOnClickOutside: false
	    			}); 
					return false;
			    }
		    }
		    else {
		    	swal({
    				title: "¡ Error !",
    				text: "Debes seleccionar una imagen o archivo para mostrar en la web",
    				icon: "error",
    				button: "continuar",
    				closeOnClickOutside: false
    			});
				return false;
		    }
		}
		$('#btn-ActionProduct').click(function(event){
			$('#imgPreview').attr('src', 'images/UploadLogo.jpg');
		});
	</script>
</body>
</html>