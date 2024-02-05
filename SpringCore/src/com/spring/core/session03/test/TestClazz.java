package com.spring.core.session03.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session03.bean.Clazz;

public class TestClazz {
	public static void main(String[] args) {
		// 計算出 beans-config3.xml 中 clazz1, clazz2, clazz3 的學分總和
		// 並分別找出學分最高與最低的科目
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		Clazz clazz1 = ctx.getBean("clazz1", Clazz.class);
		Clazz clazz2 = ctx.getBean("clazz2", Clazz.class);
		Clazz clazz3 = ctx.getBean("clazz3", Clazz.class);
		System.out.println(clazz1);
		System.out.println(clazz2);
		System.out.println(clazz3);
		
		List<Clazz> clazzs = List.of(clazz1, clazz2, clazz3);
		int sum = clazzs.stream().mapToInt(Clazz::getCredit).sum();
		System.out.printf("總學分: %d%n", sum);
		
		//clazzs.stream().mapToInt(Clazz::getCredit).forEach(System.out::println);
		int max = clazzs.stream().mapToInt(Clazz::getCredit).max().getAsInt();
		int min = clazzs.stream().mapToInt(Clazz::getCredit).min().getAsInt();
		/*
		String maxName = clazzs.stream()
							   .filter(clazz -> clazz.getCredit().equals(max))
							   .findFirst() // Optional<Clazz> 物件
							   .get() // Clazz 物件
							   .getName();
		*/
		clazzs.stream()
		   .filter(clazz -> clazz.getCredit().equals(max))
		   .findFirst()
		   .ifPresent(clazz -> System.out.printf("學分最高科目: %s 學分: %d%n", clazz.getName(), clazz.getCredit()));
		
		clazzs.stream()
		   .filter(clazz -> clazz.getCredit().equals(min))
		   .findFirst()
		   .ifPresent(clazz -> System.out.printf("學分最低科目: %s 學分: %d%n", clazz.getName(), clazz.getCredit()));
		
		
	}
}
