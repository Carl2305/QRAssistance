package com.qrassistance.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.google.gson.Gson;
import com.qrassistance.util.Check_Date_Time;

@Controller
public class AssistanceController {
	
	private static final SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
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
	public String AssistanceRegister(String cod,String nombre,String apellido, String car, HttpServletResponse response) throws IOException {
		System.out.println("ingresó al AssistanceRegister Post");
		Date date = new Date();
		ArrayList<String> responseData= new ArrayList<String>();
		if(!Check_Date_Time.isDateWith_in_Interval(formateadorFecha.format(date))) {
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

					// enviar la data a la BD para realizar el registro
										
					responseData.add(0,"Ingreso Registrado");
					responseData.add(1,"Estado: OK, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date));
					responseData.add(2,"success");
					String json=new Gson().toJson(responseData);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
					return null;
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
		}
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
	public String AssistanceCheckout(String cod,String nombre,String apellido, String car, HttpServletResponse response) throws IOException {
		System.out.println("ingresó al AssistanceCheckout Post");
		Date date = new Date();
		ArrayList<String> responseData= new ArrayList<String>();
		if(!Check_Date_Time.isDateWith_in_Interval(formateadorFecha.format(date))) {
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
		}
	}
	
	
	
}
