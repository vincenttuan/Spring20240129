package com.spring.core.session03.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session03.bean.IQ;

public class TestIQ {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		List<IQ> iqs = List.of(
				ctx.getBean("iq1", IQ.class),
				ctx.getBean("iq2", IQ.class),
				ctx.getBean("iq3", IQ.class),
				ctx.getBean("iq4", IQ.class),
				ctx.getBean("iq5", IQ.class),
				ctx.getBean("iq6", IQ.class));
		// 最高分的有那些國家 ?
		int highScore = iqs.stream().mapToInt(IQ::getScore).max().getAsInt();
		iqs.stream().filter(iq -> iq.getScore().equals(highScore))
					.forEach(System.out::println);
		
	}

}
