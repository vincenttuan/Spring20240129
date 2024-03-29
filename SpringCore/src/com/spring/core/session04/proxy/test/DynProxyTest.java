package com.spring.core.session04.proxy.test;

import com.spring.core.session04.proxy.dyn.Calc;
import com.spring.core.session04.proxy.dyn.CalcImpl;
import com.spring.core.session04.proxy.dyn.DynProxy;
import com.spring.core.session04.proxy.sta.Man;
import com.spring.core.session04.proxy.sta.Person;

public class DynProxyTest {

	public static void main(String[] args) {
		// 使用動態代理
		Calc calc = (Calc)new DynProxy(new CalcImpl()).getProxy();
		System.out.println(calc.add(20, 5));
		System.out.println(calc.div(20, 5));
		
		Person man = (Person)new DynProxy(new Man()).getProxy();
		man.work();
		
	}

}
