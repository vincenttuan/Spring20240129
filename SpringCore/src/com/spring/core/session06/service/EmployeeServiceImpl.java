package com.spring.core.session06.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.core.session06.dao.EmployeeDao;
import com.spring.core.session06.model.po.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> findAll() {
		return employeeDao.queryEmployees();
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		return employeeDao.getEmployeeById(id);
	}

	@Override
	public Employee findEmployeeByName(String name) {
		List<Employee> employees = findAll();
		Optional<Employee> employeeOpt = employees.stream().filter(emp -> emp.getName().equals(name)).findFirst();
		return employeeOpt.isPresent() ? employeeOpt.get() : null;
	}

	@Override
	public Employee findEmployeeMaxSalary() {
		List<Employee> employees = findAll();
		Integer maxSalary = employees.stream().mapToInt(Employee::getSalary).max().getAsInt();
		Optional<Employee> employeeOpt = employees.stream().filter(emp -> emp.getSalary().equals(maxSalary)).findFirst();
		return employeeOpt.isPresent() ? employeeOpt.get() : null;
	}

	@Override
	public Employee findEmployeeMinSalary() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
