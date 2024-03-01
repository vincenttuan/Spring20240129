package com.spring.core.session07.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session07.service.BookManyService;
import com.spring.core.session07.service.BookOneService;

public class BookTest2 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookManyService bookManyService = ctx.getBean(BookManyService.class);
		// 買多本書
		bookManyService.buyMany("john", 1, 2, 1);
		System.out.println("買多本書 OK");
	}
}
