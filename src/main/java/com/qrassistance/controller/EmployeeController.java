package com.qrassistance.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.qrassistance.entitylayer.Cargo;
import com.qrassistance.entitylayer.Empleado;
import com.qrassistance.entitylayer.Login;
import com.qrassistance.serviceslayer.CargoService;
import com.qrassistance.serviceslayer.EmpleadoService;
import com.qrassistance.serviceslayer.LoginService;
import com.qrassistance.util.Template_PDF_Credentials;

@Controller
public class EmployeeController {

	@Autowired
	private EmpleadoService empleadoservice;
	
	@Autowired
	private CargoService cargoservice;
	
	@Autowired
	private LoginService loginservice;
	
	private static final SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	/********************************************************/
	/********** METODOS PARA REGISTRAR UN EMPLEADO **********/
	/********************************************************/
	
	@GetMapping("/EmployeeRegister")
	public String EmployeeRegister(Model modelo) {
		System.out.println("Ingresó al EmployeeController Get");
		modelo.addAttribute("listacargos",cargoservice.ListaCargos());
		return "RegisterEmployee";
	}
	@PostMapping("/EmployeeRegister")
	public String EmployeeRegister(HttpServletResponse response,String name, String ape1, String ape2, String email, String telef, String Cargo, String UrlFoto, String Codigo, String pwd, Model modelo ) throws IOException {
		System.out.println("Ingresó al EmployeeController Post");
		String Code_QR="[\""+Codigo+"\",\""+name.toUpperCase().trim()+"\", \""+ape1.toUpperCase().trim()+" "+ape2.toUpperCase().trim()+"\",\""+Cargo+"\"]".toString();
		Date date = new Date();
		int rpta=0;
		String s="";
		try {
			Cargo car= new Cargo();
			car.setCodCargo(Cargo);
			Empleado emple= new Empleado();
			emple.setNom_empleado(name);
			emple.setApe1_empleado(ape1);
			emple.setApe2_empleado(ape2);
			emple.setCorreo_empleado(email);
			emple.setTlf_empleado(telef);
			emple.setCargo(car);
			rpta=empleadoservice.InsertEmployee(emple);
			if(rpta==1) {
				int rpta2=0;
				Login log= new Login();
				log.setUsuario(email);
				log.setContrasenia(pwd);
				log.setEmpleado(emple);
				rpta2=loginservice.InsertLogin(log);
				if(rpta2==1) {
					new Template_PDF_Credentials().CrearPDF(name.toUpperCase().trim(),ape1.toUpperCase().trim(), ape2.toUpperCase().trim(),Codigo.toUpperCase().trim(),formateadorFecha.format(date),Code_QR,UrlFoto);
					s="1";
				}else { s="0"; }
			}else { s="0"; }
		} catch (IOException e) {
			e.printStackTrace();
			s="0";
			System.out.println("Error al Generar el PDF --> "+e);
		}
		response.getWriter().write(s);
		return null;
	}
	
	
	
	
	
	
	
	/********************************************************/
	/********** METODOS PARA ACTUALIZAR UN EMPLEADO **********/
	/********************************************************/
	
	@GetMapping("/EmployeeUpdate")
	public String EmployeeUpdate() {
		System.out.println("Ingresó al EmployeeUpdate Get");
		return "UpdateEmployee";
	}
	@PostMapping("/EmployeeUpdate")
	public String EmployeeUpdate(String User) {
		System.out.println("Ingresó al EmployeeUpdate Post");
		return "UpdateEmployee";
	}
	
}
