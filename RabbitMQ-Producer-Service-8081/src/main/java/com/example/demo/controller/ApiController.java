package com.example.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/onsale/broadcast")
	public String onSaleBroascast(@RequestBody String message) {
		String data = message;
		String routingKey = ""; // fanout 不用 routingKey
		String exchangeName = "onsale-exchange";
		rabbitTemplate.convertAndSend(exchangeName, routingKey, data);
		return "On-Sale Broadcast: " + message;
	}
	
	@PostMapping("/order")
	public String order(@RequestBody String coffeeOrder) {
		String data = coffeeOrder; // 要送給 MQ 的內容 
		String routingKey = "coffee-order";
		String exchangeName = "coffee-exchange";
		// 將資料傳送給 MQ
		rabbitTemplate.convertAndSend(exchangeName, routingKey, data);
		return "Ordered a coffee: " + coffeeOrder;
	}
	
	@GetMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable Integer orderId) {
		String data = String.format("{\"orderId\": %d}", orderId);
		String routingKey = "coffee-cancel";
		String exchangeName = "coffee-exchange";
		// 將資料傳送給 MQ
		rabbitTemplate.convertAndSend(exchangeName, routingKey, data);
		return "Canceled a coffee order: " + data;
	}
	
	
}
