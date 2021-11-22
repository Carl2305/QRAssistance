<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login - QRAssistance</title>
    <!-- Load Material Icons from Google Fonts-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <!-- Roboto, Roboto Mono and kaushan-script fonts from Google Fonts-->
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&display=swap" rel="stylesheet">
    <!-- Load main stylesheet-->
    <link href="css/styles.css" rel="stylesheet">
    <style type="text/css">
    	body, html {
		    height: 100vh;
		    background-repeat: no-repeat;
		    background-image: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));
		}
		body>div{
			height:100%;
		}	
		.modal-dialog1 {
		    position: relative;
		    width: auto;
		    pointer-events: none;
		    max-width: 500px;
			margin: 0 auto;
			height: 100%;
		}
    </style>
	<script src="scripts/SweetAlert.js"></script> 
</head>
<body>
	<div id="modal_Login" tabindex="-1" role="dialog" aria-labelledby="modal_Login">
	        <div class="modal-dialog1 modal-dialog-centered" role="document">
	            <div class="modal-content" style="border-radius: 0.5rem">
	                <div class="modal-body" style="vertical-align: central; text-align: center;">
	                    <form autocomplete="off" id="frm-Login" role="form" action="Login" method="post">
		                    <div class="d-flex justify-content-center">
		                    	<img id="imgQRAssistance" class="text-center" src="" alt="" style="width: 200px; height: 180px;" />
		                        <svg style="position:absolute; top:23%; right: 27%;" xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="orange" class="bi bi-search" viewBox="0 0 16 16">
								  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
								</svg>
		                    </div>
						    <script type="text/javascript">
							    var URLactual = window.location;
							    document.getElementById("imgQRAssistance").src="http://chart.googleapis.com/chart?chs=400x400&cht=qr&chl="+URLactual;
						    </script>
	                        <div class="col-md-12 col-sm-12 col-xs-12">
	                            <h1 class="h3 mb-3 font-weight-normal">Bienvenido a <span class="font-kaushan">QRAssistance</span></h1>
	                        </div>
	                        <div class="col-md-12 col-sm-12 col-xs-12">
	                            <label for="inputEmail" class="m-2">Correo electrónico</label>
	                            <input type="email" name="email" class="form-control" placeholder="Ingresar correo" required="" autofocus="" id="email" value="admin@gmail.com"/>
	                        </div>
	                        <div class="col-md-12 col-sm-12 col-xs-12">
	                            <label for="inputPassword" class="m-2">Contraseña</label>
	                            <input type="password" name="clave" class="form-control" placeholder="Ingresar contraseña" required="" id="password" value="123"/>
	                        </div>
	                        <div class="col-md-12 col-sm-12 col-xs-12">
	                            <hr>
	                            <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn-Login" name="val" value="logIn">Iniciar sesión</button>
	                        </div>
	                    </form>
	                    <p class="mt-4 mb-3 text-muted">© 2021 <span class="font-kaushan">QRAssistance</span></p>
	                </div>
	            </div>
	        </div>
	    </div>
<%
		if(request.getAttribute("msj")!=null){
			String mensaje=(String)request.getAttribute("msj");%>
			<%=mensaje %>
		<%}else{
			System.out.println("msj >> "+request.getAttribute("msj"));
		}
	%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</body>
</html>
