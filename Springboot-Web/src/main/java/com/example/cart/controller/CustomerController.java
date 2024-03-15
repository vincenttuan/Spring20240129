package com.example.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.model.dto.CustomerDto;
import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.response.StatusMessage;
import com.example.cart.service.CustomerService;
import com.example.demo.model.response.ApiResponse;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<CustomerDto>>> queryAllCustomers() {
		List<CustomerDto> customerDtos = customerService.findAllCustomers();
		ApiResponse<List<CustomerDto>> apiResponse = new ApiResponse<>(true, StatusMessage.成功.name(), customerDtos);
		return ResponseEntity.ok(apiResponse);
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerDto>> getCustomer(@PathVariable("id") Integer id) {
		CustomerDto customerDto = customerService.getCustomerById(id);
		ApiResponse<CustomerDto> apiResponse = null;
		if (customerDto == null) {
			apiResponse = new ApiResponse<>(false, StatusMessage.查無資料.name(), null);
		} else {
			apiResponse = new ApiResponse<>(true, StatusMessage.成功.name(), customerDto);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
}
