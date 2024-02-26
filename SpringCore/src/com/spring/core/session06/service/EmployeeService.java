package com.spring.core.session06.service;

import java.util.List;

import com.spring.core.session06.model.po.Employee;

public interface EmployeeService {
	List<Employee> findAll();
	Employee findEmployeeById(Integer id);
	Employee findEmployeeByName(String name);
	Employee findEmployeeMaxSalary();
	Employee findEmployeeMinSalary();
	
}
