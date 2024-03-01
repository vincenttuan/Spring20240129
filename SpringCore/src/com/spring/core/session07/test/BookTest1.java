package com.spring.core.session07.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session07.service.BookOneService;

public class BookTest1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookOneService bookOneService = ctx.getBean(BookOneService.class);
		// 買單本書
		bookOneService.buyOne("Mary", 1);
		System.out.println("買單本書 OK");
	}
}
