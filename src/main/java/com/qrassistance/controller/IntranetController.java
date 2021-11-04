package com.qrassistance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Intranet")
public class IntranetController {

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
		if(email.equals("admin@gmail.com")&&pass.equals("123")) {
			HttpSession session= request.getSession();
			session.setAttribute("propUser", true);
			session.setAttribute("Cargo", "C01");
			session.setAttribute("Nombre", "Mogollon Espinoza");
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
			return "redirect:../";
		}
	}
	
	@GetMapping("/Logout")
	public String Logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:../";
	}
}
