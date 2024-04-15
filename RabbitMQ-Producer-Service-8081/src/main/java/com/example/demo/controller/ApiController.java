package com.example.demo.controller;

import java.util.Map;

import org.springframework.amqp.core.Message;
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
	
	// 發送不同類型的新聞到 news-exchange
	@PostMapping("/news/{category}")
	// http://localhost:8081/api/news/sports.baseball  對應到 .with("news.sports.*");
    // http://localhost:8081/api/news/tech.java        對應到 .with("news.tech.#");
    // http://localhost:8081/api/news/tech.java.spring 對應到 .with("news.tech.#");
	public String postNews(@PathVariable String category, @RequestBody String content) {
		String routingKey = "news." + category;
		String exchangeName = "news-exchange";
		rabbitTemplate.convertAndSend(exchangeName, routingKey, content);
		return "Posted news to " + category + " content:"  + content;
	}
	
	// 測試方法: http://localhost:8081/api/iot/send
    // json1: {"queueName":"temperature-data-queue","data":"25.5","headers":{"data-type":"temperature", "unit":"celsius"}}
    // json2: {"queueName":"humidity-data-queue","data":"80","headers":{"data-type":"humidity", "unit":"percentage"}}
    @PostMapping("/iot/send")
    public String sendIOTMessage(@RequestBody Map<String, Object> messageData) {
    	String queueName = (String)messageData.get("queueName");
    	String data = (String)messageData.get("data");
    	Map<String, Object> headers = (Map<String, Object>)messageData.get("headers");
    	
    	// 將 headers 放到 AMQP Message 的標頭中
    	rabbitTemplate.convertAndSend("iot-data-exchange", "", data, (Message message) -> {
    		headers.forEach((key , value) -> message.getMessageProperties().setHeader(key, value));
    		return message;
    	});
    	
    	return "Send iot message: " + messageData;
    }
	
	
}
