package com.qrassistance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qrassistance.serviceslayer.EmpleadoService;
import com.qrassistance.serviceslayer.LoginService;
import com.qrassistance.entitylayer.Empleado;
import com.qrassistance.entitylayer.Login;

@Controller
public class IntranetController {

	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private EmpleadoService empleadoservice;
	
	@RequestMapping
	public String Login() {
		System.out.println("entro al login vacio");
		return "index";
	}
	
	@PostMapping("/Login")
	public String Login(HttpServletRequest request) {
		String msj;
		String email=request.getParameter("email");
		String pass=request.getParameter("clave");
		Login login= new Login();
		login.setUsuario(email);
		login.setContrasenia(pass);
		if(loginservice.Login(login)!=0) {
			Empleado data = empleadoservice.getEmpleado(loginservice.Login(login));
			HttpSession session= request.getSession();
			session.setAttribute("propUser", true);
			session.setAttribute("CodUser", loginservice.Login(login));
			session.setAttribute("Cargo", data.getCargo().getCodCargo().toString());
			session.setAttribute("Nombre", data.getApe1_empleado()+" " +data.getNom_empleado());
			session.setMaxInactiveInterval(1800);
			return "redirect:/Home";
		}else {
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
			return "redirect:./";
		}
	}
	
	@GetMapping("/Logout")
	public String Logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:./";
	}
}
