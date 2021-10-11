<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page session="true"  %>
<%
	HttpSession misession= (HttpSession) request.getSession();
	if(misession.getAttribute("Cargo")==null){
	    response.sendRedirect("index.jsp");
	}else{
	    /* String nivel=misession.getAttribute("codCargo").toString();
	    if(!nivel.equals("A01")){
	        response.sendRedirect("index.jsp");
	    } */
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
    <title>Marcación Salida - QRAssistance</title>
    <%@include file="/templates/header.html" %>
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
</head>
<%@include file="templates/navBar.jsp" %>
	<div class="col-12">
		<h2 class="text-center pb-3 font-monospace">Muestre sus credenciales (QR) para registrar su salida</h2>
	</div>
    <div id="appCheckout" class="row">
    	<div class="col-12 col-md-3">
    		<h2>Sensores Disponibles</h2>
    		<ul>
            <li v-if="cameras.length === 0" class="empty text-danger">Error: No se encontraron sensores disponibles. Posible solición: Habilite Los permisos de para el uso del Scanner</li>
            <li v-for="camera in cameras">
              <span v-if="camera.id == activeCameraId" :title="formatName(camera.name)" class="active font-kaushan">{{ formatName(camera.name) }}</span>
              <span v-if="camera.id != activeCameraId" :title="formatName(camera.name)">
                <a @click.stop="selectCamera(camera)">{{ formatName(camera.name) }}</a>
              </span>
            </li>
          </ul>
    	</div>
    	<div class="col-12 col-md-9">
    		<div class="preview-container">
		        <video id="previewCheckout" style="width: 100%; border: 2px solid #6200ea;"></video>
		      </div>
    	</div>
    </div>
    
<%@include file="templates/footer.jsp" %>
<%@include file="templates/scripts.html" %>

    <script type="text/javascript">
	    var app = new Vue({
	      el: '#appCheckout',
	      data: {
	        scanner: null,
	        activeCameraId: null,
	        cameras: [],
	        scans: []
	      },
	      mounted: function () {
	        var self = this;
	        self.scanner = new Instascan.Scanner({ video: document.getElementById('previewCheckout'), scanPeriod: 5 });
	        self.scanner.addListener('scan', function (content, image) {
	    		let DATA=Object.values(JSON.parse(content))
	    		DataQR={cod:DATA[2],nombre:DATA[1], apellido:DATA[2],car:DATA[3]};
	    		sound.play();
	    		let d = new Date();
			    let fecha=d.getDate()+"/"+(d.getMonth()+ 1)+"/"+d.getFullYear();
			    let hora=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
    			swal({
    				title: "Salida Registrada",
    				text: "Nombre: "+DataQR.nombre+", "+DataQR.apellido+"\n"+"Fecha Asistencia: "+fecha+"\n"+"Hora Salida: "+hora,
    				icon: "success",
    				button: "OK",
    				closeOnClickOutside: false
    			});
	        });
	        Instascan.Camera.getCameras().then(function (cameras) {
	          self.cameras = cameras;
	          if (cameras.length > 0) {
	            self.activeCameraId = cameras[0].id;
	            self.scanner.start(cameras[0]);
	          } else {
	            console.error('No cameras found.');
	          }
	        }).catch(function (e) {
	          console.error(e);
	        });
	      },
	      methods: {
	        formatName: function (name) {
	          return name || '(unknown)';
	        },
	        selectCamera: function (camera) {
	          this.activeCameraId = camera.id;
	          this.scanner.start(camera);
	        }
	      }
	    });

    </script>
</body>
</html>