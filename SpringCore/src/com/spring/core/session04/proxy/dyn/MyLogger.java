package com.spring.core.session04.proxy.dyn;

import java.lang.reflect.Method;
import java.util.Arrays;

// 集中管理公用邏輯
// Aspect 切面程式 (AOP)
public class MyLogger {
	
	// Before:前置通知
	public static void before(Method method, Object[] args) {
		System.out.printf("Before:前置通知 log: 方法名稱:%s 方法參數:%s%n", 
				method.getName(), Arrays.toString(args));
	}
	
	// Exception:異常通知
	public static void throwing(Object object, Throwable e) {
		System.out.printf("%s 發生異常 %s%n", object.getClass().getSimpleName(), e);
	}
	
	// End:後置通知
	public static void end(Object object) {
		System.out.printf("%s 結束%n", object.getClass().getSimpleName());
	}
}
