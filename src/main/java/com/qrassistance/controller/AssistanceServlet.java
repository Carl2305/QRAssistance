package com.qrassistance.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().write("Nombre: "+nombre+", "+apellido+"\n"+"Fecha Asistencia: "+formateadorFecha.format(date)+"\n"+"Hora Salida: "+formateadorHora.format(date));
	}

	private void REntrada(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod=request.getParameter("cod");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String cargo=request.getParameter("car");
		Date date = new Date();
		System.out.println("entrada registrada");
		response.getWriter().write("Nombre: "+nombre+", "+apellido+"\n"+"Fecha Asistencia: "+formateadorFecha.format(date)+"\n"+"Hora Ingreso: "+formateadorHora.format(date));
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
