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
	<title>Registrar Empleado</title>
	<!-- esto sirve para cargar los stilos de la pagina -->
    <%@include file="/templates/header.html" %>
    <script type="text/javascript">var file="", boolIMG=false;</script>
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
				<input class="form-control" type="text" placeholder="Ingrese nombre" id="name_employee"/>
			</div>
			<div class="col-6 mt-2">
				<label>Apellido Paterno</label>
				<input class="form-control" type="text" placeholder="Ingrese apellido P" id="ape_paterno"/>
			</div>
			<div class="col-6 mt-2">
				<label>Apellido Materno</label>
				<input class="form-control" type="text" placeholder="Ingrese apellido M" id="ape_materno"/>
			</div>
			<div class="col-8 mt-2">
				<label>Correo</label>
				<input class="form-control" type="email" placeholder="Ingrese correo electronico" id="email_employee"/>
			</div>
			<div class="col-4 mt-2">
				<label>	DNI</label>
				<input class="form-control" type="text" placeholder="Ingrese DNI" id="code_employee"/>
			</div>
			<div class="col-6 mt-2">
				<label>Telefono</label>
				<input class="form-control" type="text" placeholder="Ingrese telefono" id="phone_employee"/>
			</div>
			<div class="col-8 mt-2">
				<label>Cargo</label>
				<form:select path="listacargos" id="cargo_employee" class="form-control">
					<form:option value="none" label="seleccione cargo"></form:option>
					<form:options items="${listacargos }" itemLabel="nombre" itemValue="codCargo" />
				</form:select>
			</div>
			<div class="col-7 mt-2">
				<label>Contraseña</label>
				<input class="form-control" type="password" placeholder="Ingrese Contraseña" id="pass_employee"/>
			</div>
			<div class="col-7 mt-2">
				<label>Confirmar Contraseña</label>
				<input class="form-control" type="password" placeholder="Ingrese Confirmación de Contraseña" id="pass_confirm_employee"/>
			</div>
		</div>
		<div class="col align-self-end">
			<label for="imgproducfile" class="form-label">Foto del Empleado:</label>
			<input type="file" id="imgproducfile" accept="image/png, image/jpeg, image/gif" hidden="" class="d-none" style="pointer-events: none;">
			<div class="row">
				<div class="col-6" id="divimgpreview">
					<img src="images/UploadLogo.jpg" class="img-fluid" name="imgPreview" id="imgPreview" style="width: 100%; max-height: 250px; border: 1px solid black;" >
					<div class="d-none" id="download_credentials">
						<h5 class="mt-2">
							<a href="http://localhost:8080/QRAssistance/pdfs/main.pdf" class="text-success" download="main.pdf">Descargar Credenciales</a>
						</h5>
					</div>
				</div>
				<div class="col-6 d-flex align-items-center">
					<button type="button" class="btn btn-info d-none" id="saveimgproduct">Subir Imagen</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col mt-4  text-center">
		<button class="btn btn-success" onclick="sendEmployee()">Registrar</button>
	</div>

	
	
	
	
	<%@include file="/templates/footer.jsp" %>
	<%@include file="/templates/scripts.html" %>
	<script>
		$("img[name=imgPreview]").click(function () {
			$('#imgproducfile').click();
			file="imgproducfile";
			$("#saveimgproduct").addClass("d-none");
		});
		$("#saveimgproduct").click(function(){
			UploadImage();
		});
		function readURL(input) {
		  if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function(e) {
		      // Asignamos el atributo src a la tag de imagen
		      $('#imgPreview').attr('src', e.target.result);
		    }
		    reader.readAsDataURL(input.files[0]);
		  }
		}
		// El listener va asignado al input
		$("#imgproducfile").change(function() {
		  readURL(this);
			$("#saveimgproduct").removeClass("d-none");
		});
		
		function UploadImage(){
			if (file != '') {
		        var filex = document.getElementById('imgproducfile');		
				var form = new FormData();
				if (filex.files.length > 0) {
		            form.append("image", filex.files[0])

		            file = "";

					var settings = {
					  "url": "https://api.imgbb.com/1/upload?key=ce434f38c832ffc0a2bf395cb9c67be5",
					  "method": "POST",
					  "timeout": 0,
					  "processData": false,
					  "mimeType": "multipart/form-data",
					  "contentType": false,
					  "data": form
					};
								
					$.ajax(settings).done(function (response) {
					  var jx = JSON.parse(response);
					  URLImage=jx.data.url;
					  $("#saveimgproduct").addClass("d-none");
					  boolIMG= true;
					  $('#imgPreview').attr('src', URLImage);
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