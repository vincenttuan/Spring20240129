package com.spring.core.session04.proxy.sta;

// 靜態代理
// 透過此類來執行代理任務
public class PersonProxy implements Person {
	
	// 被代理的物件
	private Person person;
	
	public PersonProxy(Person person) {
		this.person = person;
	}
	
	//@Override
	public void work() {
		// 公用邏輯-前置通知
		System.out.println(person.getClass().getSimpleName());
		System.out.println("戴口罩");
		System.out.println("檢查小黃卡...");
		
		// 執行(被代理物件的)業務方法
		try {
			person.work();
		} catch (Exception e) {
			// 公用邏輯-異常通知(統一捕捉錯誤)
			System.out.println(e);
		} finally {
			// 公用邏輯-後置通知(一定會被執行)
			System.out.println("脫口罩");
		}
		
	}
	
	
}
