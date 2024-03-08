package com.example.demo.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {
	
	@GetMapping("/today")
	public Date today() {
		return new Date();
	}
	
	@GetMapping("/lotto")
	public Integer lotto() {
		return new Random().nextInt(100);
	}
	
}
