package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicsConfig {
	// Topics Exchange 配置
	// 有 運動新聞, 技術新聞
	@Bean
	TopicExchange newsExchange() {
		return new TopicExchange("news-exchange");
	}
	
	@Bean
	Queue soprtsNewsQueue() {
		return new Queue("sports-news-queue", true);
	}
	
	@Bean
	Queue techNewsQueue() {
		return new Queue("tech-news-queue", true);
	}
	
	@Bean
	// "news.sports.*" `.*` 更加嚴格，僅匹配一個點劃分的單詞。
    // 例如: news.sports.football, news.sports.basketball
	Binding bindingSportsNewsQueue(Queue sportsNewsQueue, TopicExchange newsExchange) {
		return BindingBuilder.bind(sportsNewsQueue).to(newsExchange).with("news.sports.*");
	}
}
