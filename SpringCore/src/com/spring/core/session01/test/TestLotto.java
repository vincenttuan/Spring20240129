package com.spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.core.session01.bean.Lotto;
import com.spring.core.session01.config.SpringConfig;

public class TestLotto {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		Lotto lotto = ctx.getBean("lotto", Lotto.class);
		System.out.println(lotto);

	}

}
