<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Inicio - QRAssistance</title>
    <%@include file="/templates/header.html" %>
</head>
<%@include file="templates/navBar.jsp" %>
    <div id="scrollTarget"></div>
    <!-- Dashboard demos-->
    <h1 class="text-center display-2 font-kaushan">QRAssistance</h1>
    <hr />
    <div class="col-12">
        <div class="d-flex justify-content-center">
            <img id="imgQRAssistanceHome" src="" alt="" style="width: 50%;">
        </div>
        <script type="text/javascript">
	        var loc = window.location;
	        var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	        var URLactuala= loc.href.substring(0, (loc.href.length-1) - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
		    document.getElementById("imgQRAssistanceHome").src="http://chart.googleapis.com/chart?chs=400x400&cht=qr&chl="+URLactuala;
		</script>
    </div>
    
<%@include file="templates/footer.jsp" %>
<%@include file="templates/scripts.html" %>
</body>
</html>