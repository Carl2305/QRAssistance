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
    <title>Inicio - QRAssistance</title>
    <%@include file="/templates/header.html" %>
</head>
<%@include file="templates/navBar.jsp" %>
    <div id="scrollTarget"></div>
    <!-- Dashboard demos-->
    <h1 class="text-center display-2 font-kaushan">QRAssistance</h1>
    <hr />
    <div class="col-12">
        <div class="d-flex justify-content-center mt-5">
            <img id="imgQRAssistanceHome" src="" alt="" class="border-primary rounded-circle" style="width: 36%; border:20px solid;">
        </div>
        <script type="text/javascript">
	        var loc = window.location;
		    document.getElementById("imgQRAssistanceHome").src="http://chart.googleapis.com/chart?chs=400x400&cht=qr&chl="+loc;
		</script>
    </div>
    
<%@include file="templates/footer.jsp" %>
<%@include file="templates/scripts.html" %>
</body>
</html>