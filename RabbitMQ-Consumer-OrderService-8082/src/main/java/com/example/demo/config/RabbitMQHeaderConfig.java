package com.example.demo.config;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQHeaderConfig {
	@Bean
	HeadersExchange iotDataExchange() {
		return new HeadersExchange("iot-data-exchange");
	}
	
	@Bean
	Queue temperatureDataQueue() {
		return new Queue("temperature-data-queue");
	}
	
	@Bean
	Queue humidityDataQueue() {
		return new Queue("humidity-data-queue");
	}
	
	
}
