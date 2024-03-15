package com.example.cart.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Customer;
import com.example.cart.model.po.Product;

@Repository("InMemory")
public class CustomerDaoInMemory implements CustomerDao {
	
	private static List<Customer> customers = new CopyOnWriteArrayList<>();
	
	static {
		customers.add(new Customer(1, "John", "1234"));
		customers.add(new Customer(2, "Mary", "5678"));
		customers.add(new Customer(3, "Jack", "1111"));
		customers.add(new Customer(4, "Rose", "2222"));
	}
	
	@Override
	public List<Customer> findAllCustomers() {
		return customers;
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customers.stream().filter(customer-> customer.getId().equals(id)).findFirst().orElseGet(null);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		// 得到 maxId
		int maxId = customers.stream().mapToInt(Customer::getId).max().orElse(0);
		customer.setId(maxId + 1);
		customers.add(customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer updateCustomer = getCustomerById(customer.getId());
		if(updateCustomer == null) {
			return null;
		}
		updateCustomer.setUsername(customer.getUsername());
		updateCustomer.setPassword(customer.getPassword());
		return customer;
	}

	@Override
	public Boolean deleteCustomerById(Integer id) {
		return customers.removeIf(customer -> customer.getId().equals(id));
	}
	
}
