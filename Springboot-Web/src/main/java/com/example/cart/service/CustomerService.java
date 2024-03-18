package com.example.cart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.CustomerDto;
import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.po.Customer;
import com.example.cart.repository.CustomerDao;

@Service
public class CustomerService {
	
	@Autowired
	@Qualifier("InMemoryCustomer")
	private CustomerDao customerDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CustomerDto> findAllCustomers() {
		List<Customer> customers = customerDao.findAllCustomers();
		// PO 轉 DTO
		return customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDto.class))
				.toList();
	}
	
	public CustomerDto getCustomerById(Integer id) {
		Customer customer = customerDao.getCustomerById(id);
		return modelMapper.map(customer, CustomerDto.class);
	}
	
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
		Customer savedCustomer = customerDao.addCustomer(customer);
		return modelMapper.map(savedCustomer, CustomerDto.class);
	}
	
	public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
		customerDto.setId(id);
		Customer customer = modelMapper.map(customerDto, Customer.class);
		Customer updatedCustomer = customerDao.updateCustomer(customer);
		return modelMapper.map(updatedCustomer, CustomerDto.class);
	}
	
	public Boolean deleteCustomerById(Integer id) {
		return customerDao.deleteCustomerById(id);
	}
	
}
