package com.spring.core.session06.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session06.model.po.Employee;
import com.spring.core.session06.service.EmployeeService;

public class EmployeeTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmployeeService employeeService = ctx.getBean(EmployeeService.class);
		
		List<Employee> employees = employeeService.findAll();
		employees.forEach(System.out::println);
	}

}
