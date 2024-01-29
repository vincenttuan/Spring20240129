package com.spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.core.session01.bean.Hello;
import com.spring.core.session01.config.SpringConfig;

public class TestHello {

	public static void main(String[] args) {
		// 透過 Java 配置檔來取得所要的資源
		// 配置檔 SpringConfig.java
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		Hello hello = ctx.getBean("hello", Hello.class);
		System.out.println(hello);
	}

}
