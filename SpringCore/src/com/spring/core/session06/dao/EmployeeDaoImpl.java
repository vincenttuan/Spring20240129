package com.spring.core.session06.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.core.session06.model.po.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> queryEmployees() {
		String sql = "select id, name, salary, createtime from employee";
		List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
		return employees;
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getAvgSalary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(String name, Integer salary) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] batchCreate(List<Object[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateName(Integer id, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSalary(Integer id, Integer salary) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNameAndSalary(Integer id, String name, Integer salary) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
