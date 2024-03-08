package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping
	public String hello() {
		return "hello"; // 自動指向 /WEB-INF/jsp/hello.jsp
	}
	
}
