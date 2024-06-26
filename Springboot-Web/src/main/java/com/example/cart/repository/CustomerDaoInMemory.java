package com.example.cart.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Customer;
import com.example.cart.model.po.Product;

@Repository("InMemoryCustomer")
public class CustomerDaoInMemory implements CustomerDao {
	
	private static List<Customer> customers = new CopyOnWriteArrayList<>();
	
	static {
		customers.add(new Customer(1, "John", "$2a$10$JKR4ZbWg1z6zK8noz2GiNO60WcxCIY3hknzFcvAtg7Gh7mnugmKbS", "ADMIN"));
		customers.add(new Customer(2, "Mary", "$2a$10$JKR4ZbWg1z6zK8noz2GiNO60WcxCIY3hknzFcvAtg7Gh7mnugmKbS", "USER"));
		customers.add(new Customer(3, "Jack", "$2a$10$JKR4ZbWg1z6zK8noz2GiNO60WcxCIY3hknzFcvAtg7Gh7mnugmKbS", "USER"));
		customers.add(new Customer(4, "Rose", "$2a$10$JKR4ZbWg1z6zK8noz2GiNO60WcxCIY3hknzFcvAtg7Gh7mnugmKbS", "USER"));
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
		updateCustomer.setRole(customer.getRole());
		return customer;
	}

	@Override
	public Boolean deleteCustomerById(Integer id) {
		return customers.removeIf(customer -> customer.getId().equals(id));
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return customers.stream()
				.filter(customer -> customer.getUsername().equals(username))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Boolean updatePassword(Integer id, String encodedPassword) {
		Customer customer = getCustomerById(id);
		if(customer == null) {
			return false;
		}
		customer.setPassword(encodedPassword);
		return true;
	}
	
}
