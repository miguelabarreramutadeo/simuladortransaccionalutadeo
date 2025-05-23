package com.utadeo.miguelabarreram.simuladortransaccional.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping({"/", "/index", "/home"})
	public String index() {
		return "index";
	}

	@GetMapping("/welcomepage")
	public String welcomePage() {
		return "welcomepage";
	}
	
	@GetMapping("/isotemplates")
	public String isoTemplates() {
		return "isotemplates";
	}
	
	@GetMapping("/users")
	public String users() {
		return "users";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/isonodes")
	public String isoNodes() {
		return "isonodes";
	}
	
	@GetMapping("/isomsgtpls")
	public String isoMsgTpls() {
		return "isomsgtpls";
	}
	
	@GetMapping("/sendtx")
	public String sendTx() {
		return "sendtx";
	}
}
