package com.qrassistance.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qrassistance.util.Check_Date_Time;

/**
 * Servlet implementation class AssistanceServlet
 */
public class AssistanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat formateadorHora = new SimpleDateFormat("HH:mm:ss");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssistanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option=request.getParameter("op");
		switch (option) {
		case "rEntr": REntrada(request,response); break;
		case "rSali": RSalida(request,response); break;
		default: 

		}
		// {op: 'rEntr', cod: 'i201922675', nombre: 'CARLOS ALBERTO', apellido: 'MOGOLLON ESPINOZA', car: 'C019'}
		
	}

	private void RSalida(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod=request.getParameter("cod");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String cargo=request.getParameter("car");
		Date date = new Date();
		System.out.println("salida registrada");
		ArrayList<String> responseData= new ArrayList<String>();
		if(!Check_Date_Time.isDateWith_in_Interval(formateadorFecha.format(date))) {
			responseData.add(0, "Error");
			responseData.add(1,"Error, no es un dia laborable");
			responseData.add(2,"error");
			String json=new Gson().toJson(responseData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return;
		}else {
			if(!Check_Date_Time.isTimeWith_in_Interval(formateadorHora.format(date))) {
				responseData.add(0, "Error");
				responseData.add(1,"Error, Fuera del horario laboral");
				responseData.add(2,"error");
				String json=new Gson().toJson(responseData);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				return;
			}else {
				responseData.add(0,"Salida Registrada");
				responseData.add(1,"Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Salida: "+formateadorHora.format(date));
				responseData.add(2,"success");
				String json=new Gson().toJson(responseData);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		}
		
	}

	private void REntrada(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod=request.getParameter("cod");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String cargo=request.getParameter("car");
		Date date = new Date();
		System.out.println("entrada registrada");
		ArrayList<String> responseData= new ArrayList<String>();
		if(!Check_Date_Time.isDateWith_in_Interval(formateadorFecha.format(date))) {
			responseData.add(0, "Error");
			responseData.add(1,"Error, no es un dia laborable");
			responseData.add(2,"error");
			String json=new Gson().toJson(responseData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return;
		}else {
			if(!Check_Date_Time.isTimeWith_in_Interval(formateadorHora.format(date))) {
				responseData.add(0, "Error");
				responseData.add(1,"Error, Fuera del horario laboral");
				responseData.add(2,"error");
				String json=new Gson().toJson(responseData);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				return;
			}else {
				if(Check_Date_Time.isTimeDelayWhith_In_Internval(formateadorHora.format(date))) {
					responseData.add(0,"Ingreso Registrado");
					responseData.add(1,"Estado: OK, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date));
					responseData.add(2,"success");
					String json=new Gson().toJson(responseData);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
					return;
				}else {
					responseData.add(0, "Ingreso Registrado");
					responseData.add(1,"Estado: Tardanza, \n Nombre: "+nombre+", "+apellido+" \n "+"Fecha Asistencia: "+formateadorFecha.format(date)+" \n "+"Hora Ingreso: "+formateadorHora.format(date));
					responseData.add(2,"success");
					String json=new Gson().toJson(responseData);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
