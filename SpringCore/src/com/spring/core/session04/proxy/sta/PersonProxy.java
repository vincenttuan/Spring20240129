package com.spring.core.session04.proxy.sta;

// 靜態代理
// 透過此類來執行代理任務
public class PersonProxy implements Person {
	
	// 被代理的物件
	private Person person;
	
	public PersonProxy(Person person) {
		this.person = person;
	}
	
	@Override
	public void work() {
		// 公用邏輯
		System.out.println("戴口罩");
		System.out.println("檢查小黃卡...");
		// 執行(被代理物件的)業務方法
		person.work();
	}
	
	
}