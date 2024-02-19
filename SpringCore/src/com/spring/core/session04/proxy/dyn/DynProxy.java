package com.spring.core.session04.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 動態代理
public class DynProxy {
	
	// 被代理的對象
	private Object object;
	
	public DynProxy(Object object) {
		this.object = object;
	}
	
	// 取得代理對象
	public Object getProxy() {
		Object proxyObj = null;
		// 1. 被代理對象的類別載入器
		ClassLoader loader = object.getClass().getClassLoader();
		// 2. 被代理對象所實作的介面
		Class<?>[] interfaces = object.getClass().getInterfaces();
		// 3. 處理代理的實現
		InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
			// Before:前置通知
			System.out.printf("Before:前置通知 log: 方法名稱:%s 方法參數:%s%n", 
					method.getName(), Arrays.toString(args));
			Object resultObj = null;
			
			try {
				// 業務邏輯-調用
				resultObj = method.invoke(object, args); // 第一的參數要放的是被代理對象的物件
			} catch (Exception e) {
				// Exception:異常通知
				System.out.printf("%s 發生異常 %s%n", object.getClass().getSimpleName(), e);
			} finally {
				// End:後置通知
				System.out.printf("%s 結束%n", object.getClass().getSimpleName());
			}
			
			return resultObj;
		};
		
		// 取得代理實體
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}
}
