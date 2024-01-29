package com.spring.core.session01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.core.session01.bean.Hello;

@Configuration // Spring 配置檔
public class SpringConfig {
	
	@Bean(name = "hello")
	public Hello getHello() {
		Hello hello = new Hello();
		return hello;
	}
	
}
