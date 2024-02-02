package com.spring.core.session01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.spring.core.session01.bean.Hello;
import com.spring.core.session01.bean.Lotto;

@Configuration // Spring 配置檔
public class SpringConfig {
	
	@Bean(name = "hello")
	public Hello getHello() {
		Hello hello = new Hello();
		return hello;
	}
	
	@Bean(name = "lotto")
	//@Scope("singleton") // 預設-單一實體
	@Scope("prototype") // 支援多實體
	public Lotto getLotto() {
		Lotto lotto = new Lotto();
		return lotto;
	}
	
}
