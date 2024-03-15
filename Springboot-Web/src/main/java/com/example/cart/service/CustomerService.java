package com.example.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.po.Customer;
import com.example.cart.repository.CustomerDao;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> findAllCustomers() {
		return customerDao.findAllCustomers();
	}
	
	public Customer getCustomerById(Integer id) {
		return customerDao.getCustomerById(id);
	}
	
	public Customer addCustomer(Customer customer) {
		return customerDao.addCustomer(customer);
	}
	
	public Customer updateCustomer(Integer id, Customer customer) {
		customer.setId(id);
		return customerDao.updateCustomer(customer);
	}
	
	public Boolean deleteCustomer(Integer id) {
		return customerDao.deleteCustomerById(id);
	}
	
}
