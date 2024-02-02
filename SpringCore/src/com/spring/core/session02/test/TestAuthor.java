package com.spring.core.session02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session02.bean.Author;

public class TestAuthor {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml");
		Author author1 = ctx.getBean("author1", Author.class);
		author1.setName("Tom");
		author1.setSex('男');
		author1.setAge(25);
		System.out.println(author1);
		
		Author author2 = ctx.getBean("author2", Author.class);
		System.out.println(author2);
		
		Author author3 = ctx.getBean("author3", Author.class);
		System.out.println(author3);
		
		Author author4 = ctx.getBean("author4", Author.class);
		System.out.println(author4);
		
		Author author5 = ctx.getBean("author5", Author.class);
		System.out.println(author5);
	}

}
