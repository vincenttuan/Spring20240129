package com.example.cart.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.po.Product;
import com.example.cart.service.ProductService;
import com.example.demo.model.response.ApiResponse;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductDto>>> queryAllProducts() {
		List<ProductDto> productDtos = productService.queryAllProducts();
		ApiResponse<List<ProductDto>> apiResponse = new ApiResponse<>(true, "成功", productDtos);
		return ResponseEntity.ok(apiResponse);
	}
	
}
