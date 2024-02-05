package com.spring.core.session03.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session03.bean.MySQL;

public class TestMySQL {

	public static void main(String[] args) {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		MySQL mySQL = ctx.getBean("mysql", MySQL.class);
		mySQL.query();
		// ....
		ctx.close();

	}

}
