package com.example.cart.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Customer;

@Repository("InMySQLCustomer")
public class CustomerDaoInMySQL implements CustomerDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//private RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);
	
	private RowMapper<Customer> customerRowMapper = (ResultSet rs, int rowNum) -> {
		Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setUsername(rs.getString("username"));
		customer.setPassword(rs.getString("password"));
		customer.setRole(rs.getString("role"));
		return customer;
	};
	
	@Override
	public List<Customer> findAllCustomers() {
		String sql = "select id, username, password, role from customer order by id";
		return jdbcTemplate.query(sql, customerRowMapper);
	}

	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePassword(Integer id, String encodedPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
