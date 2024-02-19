package com.spring.core.session04.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
			Object resultObj = null;
			// 業務邏輯-調用
			resultObj = method.invoke(object, args); // 第一的參數要放的是被代理對象的物件
			return resultObj;
		};
		
		// 取得代理實體
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}
}