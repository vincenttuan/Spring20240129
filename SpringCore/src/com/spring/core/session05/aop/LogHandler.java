package com.spring.core.session05.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 該物件可以被 Spring 管理
@Aspect // 是一個 Aspect 切面程式 
@Order(1) // 數字越小越先被執行
public class LogHandler {
	
	// 建立一個切入點
	@Pointcut(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.add(Integer, Integer))")
	public void ptAdd() {}
	
	// 建立一個切入點
	@Pointcut(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.mul(Integer, Integer))")
	public void ptMul() {}
	
	// 建立一個切入點
	@Pointcut(value = "execution(public Integer com.spring.core.session05.aop.*.*(..))")
	public void pts() {}
		
	// 前置通知: Advice
	//@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.add(Integer, Integer))") // 切入點表達式 
	//@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.mul(Integer, Integer))")
	//@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.*(Integer, Integer))") // 忽略方法名
	//@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.*(..))") // 忽略方法名與參數
	//@Before(value = "execution(public Integer com.spring.core.session05.aop.*.*(..))") // 忽略類別, 忽略方法名與參數
	//@Before(value = "execution(* com.spring.core.session05.aop.*.*(..))") // 忽略權限+回傳值, 忽略類別, 忽略方法名與參數
	//@Before(value = "execution(* *(..))") // 全部都要攔截
	//@Before(value = "ptAdd()")
	//@Before(value = "ptAdd() || ptMul()")
	//@Before(value = "!ptAdd() && !ptMul()")
	@Before(value = "pts() && !ptMul()")
	public void beforeAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 取得連接點的方法名稱
		Object[] args = joinPoint.getArgs(); // 取得方法參數
		System.out.printf("前置通知: 名稱: %s 參數: %s%n", methodName, Arrays.toString(args));
	}
	
}
