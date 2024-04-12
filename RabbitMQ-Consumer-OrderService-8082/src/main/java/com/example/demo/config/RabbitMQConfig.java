package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Bean
	DirectExchange coffeeExchange() {
		return new DirectExchange("coffee-exchange");
	}
	
	@Bean
	Queue orderQueue() {
		return new Queue("coffee-order-queue", true);
	}
	
	@Bean
	Binding bindingOrderQueue(@Qualifier("orderQueue") Queue queue, 
							  @Qualifier("coffeeExchange") DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("coffee-order");
	}
	
}
