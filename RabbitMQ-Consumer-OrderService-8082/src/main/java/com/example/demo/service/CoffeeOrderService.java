package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CoffeeOrderService {
	
	// 處理特價廣播
	@RabbitListener(queues = {"staffQueue", "customerQueue"})
	public void receiveOnSaleBroadcast(String message) {
		System.out.println("收到特價廣播: " + message);
	}
	
	// 監聽 RabbitMQ
	@RabbitListener(queues = {"coffee-order-queue"})
	public void consumeOrderMessageFromQueue(String coffeeOrder) {
		System.out.println("Consumed a coffee order: " + coffeeOrder);
		System.out.println("接到了 coffee 訂單資料: " + coffeeOrder);
	}
	
}
