package com.globallogic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
	
	@GetMapping("/public")
	public String home() {
		return "Public";
	}
	
	@GetMapping("/private")
	public String secure() {
		return "Private";
	}

}
