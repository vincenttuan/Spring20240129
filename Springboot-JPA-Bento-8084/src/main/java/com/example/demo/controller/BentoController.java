package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bento;
import com.example.demo.service.BentoService;

@CrossOrigin
@RestController
@RequestMapping("/bento")
public class BentoController {
	
	@Autowired
	private BentoService bentoService;
	
	@GetMapping
	public List<Bento> getBentos() {
		return bentoService.getAllBentos();
	}
	
	@GetMapping("/{id}")
	public Bento getBento(@PathVariable Long id) {
		return bentoService.getBentoById(id);
	}
	
	@PostMapping
	// 模擬前端上傳的 json: {"name":"豬腳便當", "price":70, "quantity":25}
	public Bento addBento(@RequestBody Bento bento) {
		return bentoService.createBento(bento);
	}
	
}
