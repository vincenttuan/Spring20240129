package com.example.cart.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.po.Product;
import com.example.cart.model.response.StatusMessage;
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
		ApiResponse<List<ProductDto>> apiResponse = new ApiResponse<>(true, StatusMessage.成功.name(), productDtos);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> getProduct(@PathVariable("id") Integer id) {
		ProductDto productDto = productService.getProductById(id);
		ApiResponse<ProductDto> apiResponse = null;
		if (productDto == null) {
			apiResponse = new ApiResponse<>(false, StatusMessage.查無資料.name(), null);
		} else {
			apiResponse = new ApiResponse<>(true, StatusMessage.成功.name(), productDto);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestBody ProductDto productDto) {
		ProductDto savedProductDto = productService.addProduct(productDto);
		ApiResponse<ProductDto> apiResponse = new ApiResponse<>(true, StatusMessage.新增成功.name(), savedProductDto);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable("productId") Integer productId,
																 ProductDto productDto) {
		ProductDto updatedProductDto = productService.updateProduct(productId, productDto);
		ApiResponse<ProductDto> apiResponse = new ApiResponse<>(true, StatusMessage.修改成功.name(), updatedProductDto);
		return ResponseEntity.ok(apiResponse);
	}
	
	
}