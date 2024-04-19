package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
		return bentoOrderService.
	}
	
}
