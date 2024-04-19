package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BentoOrder;
import com.example.demo.service.BentoOrderService;

@CrossOrigin
@RestController
@RequestMapping("/order/bento")
public class BentoOrderController {
	
	@Autowired
	private BentoOrderService bentoOrderService;
	
	@GetMapping
	public List<BentoOrder> getBentoOrders() {
		return bentoOrderService.getAllBentoOrders();
	}
	
	@GetMapping("/{id}")
	public BentoOrder getBentoOrder(@PathVariable Long id) {
		return bentoOrderService.getBentoOrderById(id);
	}
	
	@PostMapping
	// json: {"bento": {"id":1}, "amount":2}
	public BentoOrder addBentoOrder(@RequestBody BentoOrder bentoOrder) {
		return bentoOrderService.createBentoOrder(bentoOrder);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBentoOrder(@PathVariable Long id) {
		bentoOrderService.deleteBentoOrder(id);
	}
	
	
}
