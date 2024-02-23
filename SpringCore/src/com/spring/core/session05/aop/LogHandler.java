package com.spring.core.session05.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	@Pointcut(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.div(Integer, Integer))")
	public void ptDiv() {}
		
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
	//@Before(value = "pts() && !ptMul()")
	public void beforeAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 取得連接點的方法名稱
		Object[] args = joinPoint.getArgs(); // 取得方法參數
		System.out.printf("前置通知: 名稱: %s 參數: %s%n", methodName, Arrays.toString(args));
	}
	
	// 後置通知: Advice
	//@After(value = "ptAdd()")
	public void endAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 取得連接點的方法名稱
		System.out.printf("後置通知: 名稱: %s%n", methodName);
	}
	
	// 返回通知: Advice
	// 可以透過 result 來接收到方法的回傳值
	// 若有異常發生, 則返回通知不會執行
	//@AfterReturning(value = "ptAdd()", returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) { // 返回值得型態統一是 Object
		String methodName = joinPoint.getSignature().getName(); // 取得連接點的方法名稱
		System.out.printf("返回通知: 名稱: %s 返回值: %s%n", methodName, result);
	}
	
	// 異常通知 : Advice
	// 可以透過 throwing 來設定異常通知的變數名稱
	//@AfterThrowing(value = "pts()", throwing = "e")
	public void afterThrowingAdvice(Exception e) {
		System.out.printf("發生異常通知, 將錯誤訊息: %s 寫入到 Log%n", e);
	}
	
	// 環繞通知: Advice
	// 在使用環繞通知時建議把其他通知關閉(方便學習時解讀)
	@Around(value = "ptDiv()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
		// 回傳值
		Object result = null;
		
		// 1. 環繞(前置)通知
		String methodName = joinPoint.getSignature().getName(); // 取得連接點的方法名稱
		Object[] args = joinPoint.getArgs(); // 取得方法參數
		System.out.printf("環繞(前置)通知: 名稱: %s 參數: %s%n", methodName, Arrays.toString(args));
		try {
			result = joinPoint.proceed(); // 執行 joinPoint 業務邏輯
			// 2. 環繞(返回)通知
			System.out.printf("環繞(返回)通知: 名稱: %s 返回值: %s%n", methodName, result);
			//return Integer.parseInt(result + "") + 600; // 可以有目的性的改變回傳值內容
			return result;
		} catch (Throwable e) {
			// 3. 環繞(異常)通知
			System.out.printf("發生環繞(異常)通知, 將錯誤訊息: %s 寫入到 Log%n", e);
		} finally {
			// 4. 環繞(後置)通知
			System.out.printf("環繞(後置)通知: 名稱: %s%n", methodName);
		}
		
		return result;
	}
	
}
