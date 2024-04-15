package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class IOTService {
	
	// 監聽 iot temperature-data-queue
	@RabbitListener(queues = "temperature-data-queue")
	public void receivedTemperatureData(String data) {
		System.out.println("收到現在溫度是: " + data);
	}
	
}
