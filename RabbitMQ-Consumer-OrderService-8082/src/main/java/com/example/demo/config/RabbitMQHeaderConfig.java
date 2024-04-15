package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.BindParam;

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
	
	@Bean
	Binding temperatureBinding(@Qualifier("temperatureDataQueue") Queue temperatureDataQueue,
			@Qualifier("iotDataExchange") HeadersExchange iotDataExchange) {
		Map<String, Object> headerValues = new HashMap<>();
		headerValues.put("data-type", "temperature");
		headerValues.put("unit", "celsius");
		
		// 使用 whereAll 來指定所有條件必須都匹配
		return BindingBuilder.bind(temperatureDataQueue).to(iotDataExchange).whereAll(headerValues).match();
	}
	
	
}
