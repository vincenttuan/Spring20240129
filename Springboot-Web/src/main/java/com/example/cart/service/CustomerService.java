package com.example.cart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.CustomerDto;
import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.po.Customer;
import com.example.cart.repository.CustomerDao;

@Service
public class CustomerService implements UserDetailsService {
	
	@Autowired
	@Qualifier("InMemoryCustomer")
	private CustomerDao customerDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	public CustomerDto getCustomerByUsername(String username) {
		Customer customer = customerDao.getCustomerByUsername(username);
		return modelMapper.map(customer, CustomerDto.class);
	}
	
	public Boolean updatePassword(Integer id, String encodedPassword) {
		return customerDao.updatePassword(id, encodedPassword);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerDao.getCustomerByUsername(username);
		if(customer == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return User.builder()
				.username(customer.getUsername())
				//.password(passwordEncoder.encode(customer.getPassword())) // 若密碼未加密, 須加密
				.password(customer.getPassword()) // 密碼已加密
				.roles(customer.getRole())
				.build();

	}
}
