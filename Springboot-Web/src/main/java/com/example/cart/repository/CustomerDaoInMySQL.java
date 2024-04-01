package com.example.cart.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Customer;

@Repository("InMySQLCustomer")
public class CustomerDaoInMySQL implements CustomerDao {
	
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
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
