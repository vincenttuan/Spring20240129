package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping
	public String hello(Model model) {
		// model 會自動將參數資料傳遞給 jsp
		model.addAttribute("name", "Mary");
		model.addAttribute("time", new Date());
		return "hello"; // 自動指向 /WEB-INF/jsp/hello.jsp
	}
	
}
