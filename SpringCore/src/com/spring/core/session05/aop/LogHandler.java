package com.spring.core.session05.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 該物件可以被 Spring 管理
@Aspect // 是一個 Aspect 切面程式 
@Order(1) // 數字越小越先被執行
public class LogHandler {
	
	// 前置通知: Advice
	@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.add(Integer, Integer))")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("加法前置通知");
	}
	
}
