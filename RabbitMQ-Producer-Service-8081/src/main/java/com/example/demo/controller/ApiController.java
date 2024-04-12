package com.example.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/order")
	public String order(@RequestBody String coffeeOrder) {
		String data = coffeeOrder; // 要送給 MQ 的內容 
		String routingKey = "coffee-order";
		String exchangeName = "coffee-exchange";
		// 將資料傳送給 MQ
		rabbitTemplate.convertAndSend(exchangeName, routingKey, data);
		return "Ordered a coffee: " + coffeeOrder;
	}
	
}
