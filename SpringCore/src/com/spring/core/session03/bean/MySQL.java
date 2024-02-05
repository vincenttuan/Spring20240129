package com.spring.core.session03.bean;

public class MySQL {
	public void init() {
		System.out.println("建立資料庫連線中...");
		// ...
		System.out.println("建立完成可以開始使用");
	}
	
	public void query() {
		System.out.println("資料查詢...");
	}
	
	public void destroy() {
		System.out.println("資料庫連線關閉中");
		// ...
		System.out.println("關閉成功");
	}
}
