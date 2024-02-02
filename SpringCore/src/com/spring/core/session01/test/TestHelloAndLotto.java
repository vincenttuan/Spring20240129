package com.spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session01.bean.Hello;
import com.spring.core.session01.bean.Lotto;

public class TestHelloAndLotto {
	public static void main(String[] args) {
		// 透過 beans-config.xml 配置檔來取得實體
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config.xml");
		 Hello hello1 = (Hello)ctx.getBean("hello");
		 Hello hello2 = ctx.getBean(Hello.class); // 配置檔中只有一個 Hello bean 的設定才可以用
		 Hello hello3 = ctx.getBean("hello", Hello.class); // 建議使用
		 
		 Lotto lotto1 = ctx.getBean("lotto", Lotto.class);
		 Lotto lotto2 = ctx.getBean("lotto", Lotto.class);
		 
		 System.out.println(hello1);
		 System.out.println(hello2);
		 System.out.println(hello3);
		 System.out.println(lotto1);
		 System.out.println(lotto2);
		 
		  
		
	}
}
