package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NewsOrderService {
	
	// 監聽運動新聞對列
	@RabbitListener(queues = "sports-news-queue")
	public void consumeSportsNews(String message) {
		System.out.println("接收運動新聞: " + message);
	}
	
	// 監聽技術新聞對列
	@RabbitListener(queues = "tech-news-queue")
	public void consumeTechNews(String message) {
		System.out.println("接收技術新聞: " + message);
	}
}
