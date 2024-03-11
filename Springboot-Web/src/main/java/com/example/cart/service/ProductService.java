package com.example.cart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.po.Product;
import com.example.cart.repository.ProductDao;

@Service
public class ProductService {
	@Autowired
	@Qualifier("InMemory")
	private ProductDao productDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ProductDto> queryAllProducts() {
		List<Product> products = productDao.queryAllProducts();
		// PO 轉 DTO
		return products.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.toList();
	}
	
	public ProductDto getProductById(Integer id) {
		if(id == null) {
			return null;
		}
		Product product = productDao.getProductById(id);
		return modelMapper.map(product, ProductDto.class);
	}
	
	public ProductDto addProduct(Product product) {
		// 檢查 product 的資料 ...
		// 略...
		Product savedProduct = productDao.addProduct(product);
		return modelMapper.map(savedProduct, ProductDto.class);
	}
	
	public ProductDto updateProduct(Integer id, Product product) {
		if(id == null) {
			return null;
		}
		product.setId(id);
		Product updatedProduct = productDao.updateProduct(product);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}
	
	public ProductDto updateProductQty(Integer id, Integer qty) { // 修改庫存(庫存覆蓋)
		if(id == null || qty == null || qty < 0) {
			return null;
		}
		Product updatedProduct = productDao.updateProductQty(id, qty);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}
	
	public ProductDto addProductQty(Integer id, Integer increment) { // 增量庫存
		if(id == null || increment == null || increment < 0) {
			return null;
		}
		Product updatedProduct = productDao.addProductQty(id, increment);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}
	
	public ProductDto reduceProductQty(Integer id, Integer decrement) { // 減量庫存
		if(id == null || decrement == null || decrement < 0) {
			return null;
		}
		// 減量後庫存不可 < 0
		Product product = productDao.getProductById(id);
		if(product.getQty() - decrement < 0) {
			return null;
		}
		Product updatedProduct = productDao.reduceProductQty(id, decrement);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}
	
	public Boolean deleteProductById(Integer id) {
		return id == null ? false : productDao.deleteProductById(id);
	}
}
