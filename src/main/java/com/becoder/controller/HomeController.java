package com.becoder.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

	@GetMapping("/")
	public String getIndex(HttpServletRequest request) {
		return "Welcome to Index Rest API SessionToken :"+request.getSession().getId();
	}
	@GetMapping("/csrf_token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	/*
	 * @GetMapping("/home") 
	 * public String getHome() 
	 * { 
	 * 		return "home"; 
	 * }
	 * 
	 * @GetMapping("/about") 
	 * public String getAbout() 
	 * { 
	 * 		return "about"; 
	 * }
	 */
}
