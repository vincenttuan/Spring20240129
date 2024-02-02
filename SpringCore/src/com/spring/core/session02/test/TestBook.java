package com.spring.core.session02.test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session02.bean.Author;
import com.spring.core.session02.bean.Book;

public class TestBook {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml");
		// 手動注入
		Author author1 = ctx.getBean("author1", Author.class);
		author1.setName("Tom");
		author1.setSex('男');
		author1.setAge(25);
		Book book1 = ctx.getBean("book1", Book.class);
		book1.setName("Android");
		book1.setPrice(420);
		book1.setAuthor(author1);
		// 透過 DI 自動注入
		Book book2 = ctx.getBean("book2", Book.class);
		Book book3 = ctx.getBean("book3", Book.class);
		Book book4 = ctx.getBean("book4", Book.class);
		Book book5 = ctx.getBean("book5", Book.class);
		Book book6 = ctx.getBean("book6", Book.class);
		Book book7 = ctx.getBean("book7", Book.class);
		Book book8 = ctx.getBean("book8", Book.class);
		
		List<Book> books = List.of(book1, book2, book3, book4, book5, book6, book7, book8);
		// 1.請計算並印出所有書籍的總價格 ?
		int totalPrice = books.stream().mapToInt(Book::getPrice).sum();
		
		// 2.請計算並印出作者為男性的書籍總價 ?
		int totalPriceMaleAuthors = books.stream()
				.filter(book -> book.getAuthor().getSex().equals('男'))
				.mapToInt(Book::getPrice)
				.sum();
		
		// 3.請計算並印出作者為女性的書籍總價 ?
		int totalPriceFemaleAuthors = books.stream()
				.filter(book -> book.getAuthor().getSex().equals('女'))
				.mapToInt(Book::getPrice)
				.sum();
		
		// 4.請計算出 book1 ~ book8 作者的平均年齡 ?
		double averageAge = books.stream()
				.map(Book::getAuthor)
				.collect(Collectors.toSet())
				.stream()
				.mapToInt(Author::getAge)
				.average()
				.getAsDouble();
		
		System.out.printf("所有書籍的總價格 %,d%n", totalPrice);
		System.out.printf("作者為男性的書籍總價 %,d%n", totalPriceMaleAuthors);
		System.out.printf("作者為女性的書籍總價 %,d%n", totalPriceFemaleAuthors);
		System.out.printf("作者的平均年齡 %.2f%n", averageAge);
		
		
	}

}
