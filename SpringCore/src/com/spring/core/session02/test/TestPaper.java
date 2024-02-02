package com.spring.core.session02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.spring.core.session02.bean.Paper;

public class TestPaper {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml");
		Paper paper1 = ctx.getBean("paper1", Paper.class);
		System.out.println(paper1);
	}

}
