package com.qrassistance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/Home")
	public String Index() {
		System.out.println("Ingreso al home Get");
		return "Home";
	}
}
