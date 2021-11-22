package com.qrassistance.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.google.gson.Gson;
import com.qrassistance.entitylayer.Empleado;
import com.qrassistance.entitylayer.Marcacion;
import com.qrassistance.serviceslayer.AssistanceService;

@Controller
public class AssistanceController {
	
	@Autowired 
	private AssistanceService assistanceservice;
	
	private static final SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat formateadorFecha2 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formateadorHora = new SimpleDateFormat("HH:mm:ss");   
	
	/*******************************************************/
	/********** METODOS PARA REGISTRAR LA ENTRADA **********/
	/*******************************************************/
	
	@GetMapping("/AssistanceRegister")
	public String AssistanceRegister() {
		System.out.println("ingresó al AssistanceRegister Get");
		return "RegisterAssistance";
	}
	@PostMapping("/AssistanceRegister")
	public String AssistanceRegister(String cod,String nombre,String apellido, String car, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ingresó al AssistanceRegister Post");
		Date date = new Date();
		ArrayList<String> responseData= new ArrayList<String>();
		
		/*********/
		//Zona de Prueba de envio de datos sin validaciones de horario
		//aqui puede pegar el codigo par hacer pruebas.
		// para que no te salga error, debes comentar las lineas de validacion IFs
		
		boolean rpta= false;
		Marcacion marca= new Marcacion();
		Empleado emple= new Empleado();
		emple.setCod_empleado((int)request.getSession().getAttribute("CodUser"));
		marca.setInicio(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		marca.setEmpleado(emple);
		
		rpta=assistanceservice.RegistroIngresoAsistencia(marca);
		if(rpta) {
			responseData.add(0,"Ingreso Registrado");
			responseData.add(1,"Estado: OK, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date));
			responseData.add(2,"success");
			String json=new Gson().toJson(responseData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return null;
		}else {
			responseData.add(0,"¡ Error !");
			responseData.add(1,"Por favor intentalo nuevamente");
			responseData.add(2,"error");
			String json=new Gson().toJson(responseData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return null;
		}
		
		/********/
		
		/*if(!Check_Date_Time.isDateWith_in_Interval(formateadorFecha.format(date))) {
			responseData.add(0, "Error");
			responseData.add(1,"Error, no es un dia laborable");
			responseData.add(2,"error");
			String json=new Gson().toJson(responseData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return null;
		}else {
			if(!Check_Date_Time.isTimeWith_in_Interval(formateadorHora.format(date))) {
				responseData.add(0, "Error");
				responseData.add(1,"Error, Fuera del horario laboral");
				responseData.add(2,"error");
				String json=new Gson().toJson(responseData);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				return null;
			}else {
				if(Check_Date_Time.isTimeDelayWhith_In_Internval(formateadorHora.format(date))) {

					boolean rpta= false;
					Marcacion marca= new Marcacion();
					Empleado emple= new Empleado();
					marca.setInicio(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
					
					rpta=assistanceservice.RegistroIngresoAsistencia(marca);
					if(rpta) {
						responseData.add(0,"Ingreso Registrado");
						responseData.add(1,"Estado: OK, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date));
						responseData.add(2,"success");
						String json=new Gson().toJson(responseData);
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(json);
						return null;
					}else {
						responseData.add(0,"¡ Error !");
						responseData.add(1,"Por favor intentalo nuevamente");
						responseData.add(2,"error");
						String json=new Gson().toJson(responseData);
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(json);
						return null;
					}				
				}else {

					// enviar la data a la BD para realizar el registro
					
					responseData.add(0, "Ingreso Registrado");
					responseData.add(1,"Estado: Tardanza, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date));
					responseData.add(2,"success");
					String json=new Gson().toJson(responseData);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
					return null;
				}
			}
		}*/
	}
	
	
	/******************************************************/
	/********** METODOS PARA REGISTRAR LA SALIDA **********/
	/******************************************************/
	
	@GetMapping("/AssistanceCheckout")
	public String AssistanceCheckout() {
		System.out.println("ingresó al AssistanceCheckout Get");
		return "RegisterCheckout";
	}
	
	@PostMapping("/AssistanceCheckout")
	public String AssistanceCheckout(String cod,String nombre,String apellido, String car, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ingresó al AssistanceCheckout Post");
		Date date = new Date();
		ArrayList<String> responseData= new ArrayList<String>();
		String rptaTitle="¡ Error !";
		String rptaBody="Por favor intentalo nuevamente";
		String rptaStatus="error";
		
		List<Marcacion> data=assistanceservice.ListaAsistencia();
		for(Marcacion item :data) {
			if(item!=null) {
				if(item.getEmpleado().getCod_empleado()==(int)request.getSession().getAttribute("CodUser")) {
					String fecha_marca=item.getInicio().toString().substring(0, 10);
					if(fecha_marca.equals(formateadorFecha2.format(date))) {
						/*********/
						//Zona de Prueba de envio de datos sin validaciones de horario
						//aqui puede pegar el codigo par hacer pruebas.
						// para que no te salga error, debes comentar las lineas de validacion IFs
												
						boolean rpta= false;
						Marcacion marca= new Marcacion();
						Empleado emple= new Empleado();
						marca.setFin(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
						marca.setMarcacion(item.getMarcacion());
						
						rpta=assistanceservice.RegistroSalidaAsistencia(marca);
						if(rpta) {
							rptaTitle="Salida Registrada";
							rptaBody="Estado: OK, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date);
							rptaStatus="success";
							
						}else {
							rptaTitle="¡ Error !";
							rptaBody="Por favor intentalo nuevamente";
							rptaStatus="error";
						}		
						
						
						/**************/
						
						
						/*if(!Check_Date_Time.isDateWith_in_Interval(formateadorFecha.format(date))) {
							responseData.add(0, "Error");
							responseData.add(1,"Error, no es un dia laborable");
							responseData.add(2,"error");
							String json=new Gson().toJson(responseData);
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write(json);
							return null;
						}else {
							if(!Check_Date_Time.isTimeWith_in_Interval(formateadorHora.format(date))) {
								
								// enviar la data a la BD para realizar el registro
								
								responseData.add(0,"Salida Registrada");
								responseData.add(1,"Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Salida: "+formateadorHora.format(date));
								responseData.add(2,"success");
								String json=new Gson().toJson(responseData);
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write(json);
								return null;
							}else {
								responseData.add(0, "Error");
								responseData.add(1,"Error, Fuera del horario laboral");
								responseData.add(2,"error");
								String json=new Gson().toJson(responseData);
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write(json);
								return null;
							}
						}*/
					}else {
						rptaTitle="¡ Error !";
						rptaBody="Por favor intentalo nuevamente";
						rptaStatus="error";
					}
				}else {
					rptaTitle="¡ Error !";
					rptaBody="Por favor intentalo nuevamente";
					rptaStatus="error";
				}
			}else {
				rptaTitle="¡ Error !";
				rptaBody="Por favor intentalo nuevamente";
				rptaStatus="error";
			}
		}
		responseData.add(0,rptaTitle);
		responseData.add(1,rptaBody);
		responseData.add(2,rptaStatus);
		String json=new Gson().toJson(responseData);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		return null;
	}
	
	
	/****************************************************************/
	/********** METODOS PARA EL LISTADO DE LAS MARCACIONES **********/
	/****************************************************************/
	
	@GetMapping("/AssistanceHistory")
	public String AssistanceHistory(HttpServletRequest request, Model model) {
		System.out.println("ingresó al AssistanceHistory Get");
		List<Marcacion> data=assistanceservice.ListaAsistencia();
		List<Marcacion>ListMarcacionEmple=new ArrayList<Marcacion>();
		for(Marcacion item :data) {
			if(item!=null) {
				if(item.getEmpleado().getCod_empleado()==(int)request.getSession().getAttribute("CodUser")) {
					ListMarcacionEmple.add(item);
				}
			}
		}
		model.addAttribute("listaAssitance",ListMarcacionEmple);
		return "HistoryAssistance";
	}
	
	@PostMapping("/HistoryAssistance")
	public String AssistanceHistory(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("ingresó al AssistanceHistory Post");
		return null;
	}
	
	
	
}
