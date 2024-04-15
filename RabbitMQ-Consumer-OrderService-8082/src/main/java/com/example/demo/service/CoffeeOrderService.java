package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CoffeeOrderService {
	
	// 處理特價廣播
	
	// log1 與 log2 會輪流接收
	@RabbitListener(queues = {"logQueue"})
	public void receiveLog1(String message) {
		//System.out.println("統一收到特價廣播: " + message);
		System.out.println("寫 log1: " + message);
	}
	
	@RabbitListener(queues = {"logQueue"})
	public void receiveLog2(String message) {
		//System.out.println("統一收到特價廣播: " + message);
		System.out.println("寫 log2: " + message);
	}
	
	@RabbitListener(queues = {"staffQueue"})
	public void receiveOnSaleToStaff(String message) {
		System.out.println("staff 收到特價廣播發 email: " + message);
	}
	
	@RabbitListener(queues = {"customerQueue"})
	public void receiveOnSaleToCustomer(String message) {
		System.out.println("customer 收到特價廣播發 sms: " + message);
	}
	
	
	// 監聽 RabbitMQ
	@RabbitListener(queues = {"coffee-order-queue"})
	public void consumeOrderMessageFromQueue(String coffeeOrder) {
		System.out.println("Consumed a coffee order: " + coffeeOrder);
		System.out.println("接到了 coffee 訂單資料: " + coffeeOrder);
	}
	
}
