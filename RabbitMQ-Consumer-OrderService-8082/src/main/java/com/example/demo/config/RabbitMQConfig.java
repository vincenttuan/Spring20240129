package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	// Fanout ---------------------------------------------------------------------
	/**                          
	       +-----------------+ ---->  staffQueue
	 ----> | onsale-exchange | 
	       +-----------------+ ---->  customerQueue
	                             ^
	                             |--- binding
	 * */
	
	@Bean
	FanoutExchange onSaleExchange() {
		return new FanoutExchange("onsale-exchange"); // 廣播促銷
	}
	
	@Bean
	Queue staffQueue() { // 員工對列
		return new Queue("staffQueue", true);
	}
	
	@Bean
	Queue customerQueue() { // 顧客對列
		return new Queue("customerQueue", true);
	}
	
	@Bean
	Queue logQueue() { // Log對列
		return new Queue("logQueue", true);
	}
	
	@Bean
	Binding staffBinding(@Qualifier("staffQueue") Queue queue, @Qualifier("onSaleExchange") FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	@Bean
	Binding customerBinding(Queue customerQueue, FanoutExchange onSaleExchange) {
		return BindingBuilder.bind(customerQueue).to(onSaleExchange);
	}
	
	// Log ------------------------------------------------------------------------
	@Bean
	Binding logOnSaleBinding(Queue logQueue, FanoutExchange onSaleExchange) {
		return BindingBuilder.bind(logQueue).to(onSaleExchange);
	}
	
	@Bean
	Binding logOrderBinding(Queue logQueue, DirectExchange coffeeExchange) {
		return BindingBuilder.bind(logQueue).to(coffeeExchange).with("coffee-order");
	}
	
	// Direct ---------------------------------------------------------------------
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
