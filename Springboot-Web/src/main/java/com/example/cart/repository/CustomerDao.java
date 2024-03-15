package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.po.Customer;

public interface CustomerDao {
	List<Customer> findAllCustomers();
	Customer getCustomerById(Integer id);
	Customer addCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Boolean deleteCustomerById(Integer id);
	
}
