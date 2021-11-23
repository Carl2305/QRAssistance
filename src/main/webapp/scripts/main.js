/**
 * @author Mogollon Espinoza Carlos
 */
// instancia objeto Audio
var sound= new Audio("sounds/barcode.wav");

var sendEmployee = function()
{
	if(boolIMG===true){
		if($("#pass_employee").val().trim()===$("#pass_confirm_employee").val().trim()){
			$.ajax({
				url: 'EmployeeRegister', 
				type: 'POST',
				dataType: 'json',
				async: true,
				data: {	
					'name':$("#name_employee").val().trim(),
					'ape1':$("#ape_paterno").val().trim(),
					'ape2':$("#ape_materno").val().trim(),
					'email':$("#email_employee").val().trim(),
					'telef':$("#phone_employee").val().trim(),
					'Cargo':$("#cargo_employee").val(),
					'UrlFoto':$('#imgPreview').attr("src"),
					'Codigo':$("#code_employee").val().trim(),
					'pwd':$("#pass_employee").val().trim(),
					'ar':$("#area_employee").val(),
					'emsupe':$("#super_employee").val()
				},
				success: function(data){
					if(data=='1'){
						swal({
		    				title: "¡ Registro Exitoso !",
		    				text: "Empleado Registrado.",
		    				icon: "success",
		    				button: "continuar"
		    			}).then((willDelete) => {
	                    window.setTimeout(function () {
	                       $("#download_credentials").removeClass("d-none");
	                    }, 100);
	                });
						boolIMG=false;
					}else{
						swal({
		    				title: "¡ Registro Fallido !",
		    				text: "",
		    				icon: "error",
		    				button: "continuar"
		    			});
						URLImage=""; 
						boolIMG=false;
					}
				},
				error:function(e){
					console.log('errorajax: '+ e.getMessage);
				}
			});
		}else{
			swal({
		    	title: "¡ Las Contraseñas No Coinciden !",
		    	text: "Verifique que sus contraseñas sean iguales.",
		    	icon: "warning",
		    	button: "continuar"
		    });
		}
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