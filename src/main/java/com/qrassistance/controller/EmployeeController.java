package com.qrassistance.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrassistance.entitylayer.Area;
import com.qrassistance.entitylayer.Cargo;
import com.qrassistance.entitylayer.Empleado;
import com.qrassistance.entitylayer.Login;
import com.qrassistance.serviceslayer.AreaService;
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
	
	@Autowired AreaService areaservice;
	
	private static final SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	/********************************************************/
	/********** METODOS PARA REGISTRAR UN EMPLEADO **********/
	/********************************************************/
	
	@GetMapping("/EmployeeRegister")
	public String EmployeeRegister(HttpServletRequest request, Model modelo) {
		System.out.println("Ingresó al EmployeeController Get");
		List<Empleado> DATABD= empleadoservice.ListaEmpleados();
		ArrayList<Empleado> dataView=new ArrayList<Empleado>();
		for(Empleado emple:DATABD) {
			if(emple.getCargo().getCodCargo().toString().equals("C01")) {
				dataView.add(emple);
			}
		}

		modelo.addAttribute("listacargos",cargoservice.ListaCargos());
		modelo.addAttribute("listasupervisor", dataView);
		modelo.addAttribute("listaareas",areaservice.ListaAreas());
		return "RegisterEmployee";
	}
	@PostMapping("/EmployeeRegister")
	public String EmployeeRegister(HttpServletResponse response,String name, String ape1, String ape2, 
									String email, String telef, String Cargo, String UrlFoto, String Codigo, 
									String pwd, String ar, int emsupe, Model modelo ) throws IOException {
		System.out.println("Ingresó al EmployeeController Post");
		String Code_QR="[\""+Codigo+"\",\""+name.toUpperCase().trim()+"\", \""+ape1.toUpperCase().trim()+" "+ape2.toUpperCase().trim()+"\",\""+Cargo+"\"]".toString();
		Date date = new Date();
		int rpta=0;
		String s="";
		try {
			Cargo car= new Cargo();
			car.setCodCargo(Cargo);
			Area area= new Area();
			area.setCod_area(ar);
			Empleado superEm= new Empleado();
			superEm.setCod_empleado(emsupe);
			Empleado emple= new Empleado();
			emple.setNom_empleado(name);
			emple.setApe1_empleado(ape1);
			emple.setApe2_empleado(ape2);
			emple.setCorreo_empleado(email);
			emple.setTlf_empleado(telef);
			emple.setFoto_empleado(UrlFoto);
			emple.setCargo(car);
			emple.setArea(area);
			emple.setEmpleado(superEm);
			emple.setFecha_registro(formateadorFecha.format(date));
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
	
	/*
	 * el porblema es que envias el codigo aqui mismo como parametro y a lo que me retorna  a la misma 
	 * vista, me exige el parametro aaaaaaaaaaaaa si enviamos el paramet pero si ya lo enviamos no ? , en el html esta hidden ,s el js no lo recibe igual o es que get y post 
	 * necesitan el mismo nom para cod ?, no
	 * y que pasa si lo llevamos a listEmpleado ?
	 * 
	 * esque el post me retorna nulo, si eso estaba pensando, peor como mustras la alerta de actualizado, ese es el problema, primero saldra el error antes de que muestre una
	 * alert ? 
	 * */
	
	@GetMapping("/EmployeeUpdate")
	public String EmployeeUpdate(@RequestParam("cod") int cod,Model model) {
		System.out.println("Ingresó al EmployeeUpdate Get");
		
		Empleado em = new Empleado();
		em=empleadoservice.getEmpleado(cod);
		List<Empleado> DATABD= empleadoservice.ListaEmpleados();
		ArrayList<Empleado> dataView=new ArrayList<Empleado>();
		for(Empleado emple:DATABD) {
			if(emple.getCargo().getCodCargo().toString().equals("C01")) {
				dataView.add(emple);
			}
		}
		
		model.addAttribute("e",em);
		model.addAttribute("listacargos",cargoservice.ListaCargos());
		model.addAttribute("listasupervisor", dataView);
		model.addAttribute("listaareas",areaservice.ListaAreas());
		return "UpdateEmployee";
	}
	
	@PostMapping("/EmployeeUpdatee")
	public String EmployeeUpdate(String empleadoCo, String name, String ape1, String ape2, 
			String email, String telef, String Cargo, String UrlFoto, String Codigo, 
			String pwd, String ar, String emsupe, String fecha, Model model) {
		
		System.out.println("Ingresó al EmployeeControllerUpdate Post");
		// la fecha esta mal, allí lo arreglas
		// el resto ya esta , añadamos en el cliente el atributo fecha para que se guarde y cuando haga pdf en en modificar se quede siempre la misma
		// mucha logica, como asi  ? solo cuando se registra se guarda una fecha y cuando mofidiquemos solo jalamos la fecha guardada
		// la fecha se alamcena o no, se almacena

		
		
		int rpta=0;
		String s="";
		
		if(name!=null) {
			String Code_QR="[\""+Codigo+"\",\""+name.toUpperCase().trim()+"\",\""+ape1.toUpperCase().trim()+" "+ape2.toUpperCase().trim()+"\",\""+Cargo+"\"]".toString();

			try {			
				
				Cargo car= new Cargo();
				car.setCodCargo(Cargo);
				Area area= new Area();
				area.setCod_area(ar);
				Empleado superEm= new Empleado();
				superEm.setCod_empleado(Integer.parseInt(emsupe));
				Empleado emple= new Empleado();
				emple.setCod_empleado(Integer.parseInt(empleadoCo));
				emple.setNom_empleado(name);
				emple.setApe1_empleado(ape1);
				emple.setApe2_empleado(ape2);
				emple.setCorreo_empleado(email);
				emple.setTlf_empleado(telef);
				emple.setFoto_empleado(UrlFoto);
				emple.setCargo(car);
				emple.setArea(area);
				emple.setEmpleado(superEm);
				rpta=empleadoservice.UpdateEmployee(emple);
				if(rpta==1) {
					// vas a tener que obtener la fecha en que se registro el usuario para poder enviarla en este metodo del generar el PDF
					new Template_PDF_Credentials().CrearPDF(name.toUpperCase().trim(),ape1.toUpperCase().trim(), ape2.toUpperCase().trim(),Codigo.toUpperCase().trim(),fecha,Code_QR,UrlFoto);
					s="1";
				}else { s="0"; }
			} catch (Exception e) {
				s="0";
				System.out.println("Error al Generar el PDF --> "+e);
			}
		}
		model.addAttribute("status", s);
		model.addAttribute("listaEmployee",empleadoservice.ListaEmpleados());
		return "ListEmployeeAll";
	}
	
	
	/****************************************************************/
	/********** METODO PARA ELIMINAR UN EMPLEADO **********/
	/****************************************************************/
	
	@RequestMapping("/DeleteEmployee")
	public String DeleteEmployee(@ModelAttribute("cod")int cod,Model modelo) 
	{
		empleadoservice.DeleteEmployee(cod);
		
		modelo.addAttribute("listaEmployee",empleadoservice.ListaEmpleados());
		return "ListEmployeeAll";
	}
	
	
	
	/****************************************************************/
	/********** METODOS PARA EL LISTADO DE LOS EMPLEADOS **********/
	/****************************************************************/
	
	@GetMapping("/TotalEmployee")
	public String TotalEmployee (Model model)
	{
		model.addAttribute("listaEmployee",empleadoservice.ListaEmpleados());
		return "EmployeeRecord";
	}
	
	
	
	@GetMapping("/EmployeeListAll")
	public String ListAllEmployee(Model model) {
		model.addAttribute("listaEmployee",empleadoservice.ListaEmpleados());
		return "ListEmployeeAll";
	}
	
	
}
