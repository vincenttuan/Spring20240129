package com.spring.core.session06.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.session06.dao.EmployeeDao;
import com.spring.core.session06.model.po.Employee;

//@Service("myEmployeeService")
@Service // 預設 employeeServiceImpl
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	@Qualifier("myEmployeeDao")
	// 會自動去尋找 EmployeeDao 的實作類
	// 在這邊就會自動找到 EmployeeDaoImpl
	// 相當於 private EmployeeDao employeeDao = new EmployeeDaoImpl();
	// 限制在 EmployeeDao 只有一個實作類的情況下 
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
		/*
		Integer maxSalary = employees.stream().mapToInt(Employee::getSalary).max().getAsInt();
		Optional<Employee> employeeOpt = employees.stream().filter(emp -> emp.getSalary().equals(maxSalary)).findFirst();
		return employeeOpt.isPresent() ? employeeOpt.get() : null;
		*/
		Employee employee = employees.stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
		return employee;
	}

	@Override
	public Employee findEmployeeMinSalary() {
		return findAll().stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
	}
	
}
