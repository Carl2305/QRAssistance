package com.qrassistance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

	/********************************************************/
	/********** METODOS PARA REGISTRAR UN EMPLEADO **********/
	/********************************************************/
	
	@GetMapping("/EmployeeRegister")
	public String EmployeeRegister() {
		System.out.println("Ingresó al EmployeeController Get");
		return "RegisterEmployee";
	}
	@PostMapping("/EmployeeRegister")
	public String EmployeeRegister(String User) {
		System.out.println("Ingresó al EmployeeController Post");
		return "RegisterEmployee";
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
