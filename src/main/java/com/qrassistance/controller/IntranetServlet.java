package com.qrassistance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IntranetServlet
 */
public class IntranetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntranetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option=request.getParameter("val");
		switch (option) {
		case "logIn": logIn(request,response); break;
		case "cPss": cambioPass(request,response); break;
		default: logOut(request,response); break;
		}		
	}

	private void cambioPass(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

	private void logIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String msj;
		String email=request.getParameter("email");
		String pass=request.getParameter("clave");
		
		if(email.equals("admin@gmail.com")&&pass.equals("123")) {
			HttpSession session= request.getSession();
			session.setAttribute("Cargo", "Administrador");
			session.setAttribute("Nombre", "Mogollon Espinoza");
			session.setAttribute("User", "i201922675");
			session.setMaxInactiveInterval(1800);
			response.sendRedirect("Home.jsp");
		}else {
			System.out.println("else error de login");
			msj="<script type=\"text/javascript\">\r\n"
					+ "swal({\r\n"
					+ "	  title: \"Error !\",\r\n"
					+ "	  text: \"Usuario y/o Contraseña son Invalidos\",\r\n"
					+ "	  icon: \"error\",\r\n"
					+ "	  button: \"OK\",\r\n"
					+ "	  timer: 2000\r\n"
					+ "	});\r\n"
					+ "</script>";
			request.setAttribute("msj", msj);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
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
