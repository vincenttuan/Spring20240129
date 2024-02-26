package com.spring.core.session06.dao;

import java.util.List;

import com.spring.core.session06.model.po.Employee;

public interface EmployeeDao {
	// 多筆查詢
	List<Employee> queryEmployees();
	
	// 單筆查詢
	Employee getEmployeeById(Integer id);
	
	// 資料筆數
	Integer count();
	
	// 查詢平均薪資
	Double getAvgSalary();
	
	// 新增單筆
	int create(String name, Integer salary);
	
	// 新增多筆
	int[] batchCreate(List<Object[]> list);
	
	// 修改
	int updateName(Integer id, String name);
	
	int updateSalary(Integer id, Integer salary);
	
	int updateNameAndSalary(Integer id, String name, Integer salary);
	
	// 刪除
	int deleteEmployeeById(Integer id);
}
