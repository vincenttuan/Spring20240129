package com.example.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.cart.repository.ProductDao;

@Service
public class ProductService {
	@Autowired
	@Qualifier("InMemory")
	private ProductDao productDao;
	
}
