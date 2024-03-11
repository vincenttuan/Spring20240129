package com.example.cart.service;

import java.util.List;

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
	
	public List<ProductDto> queryAllProducts() {
		List<Product> products = productDao.queryAllProducts();
		// PO 轉 DTO
		
	}
	
	public Product getProductById(Integer id) {
		if(id == null) {
			return null;
		}
		return productDao.getProductById(id);
	}
	
	public Product addProduct(Product product) {
		// 檢查 product 的資料 ...
		// 略...
		return productDao.addProduct(product);
	}
	
	public Product updateProduct(Integer id, Product product) {
		if(id == null) {
			return null;
		}
		product.setId(id);
		return productDao.updateProduct(product);
	}
	
	public Product updateProductQty(Integer id, Integer qty) { // 修改庫存(庫存覆蓋)
		if(id == null || qty == null || qty < 0) {
			return null;
		}
		return productDao.updateProductQty(id, qty);
	}
	
	public Product addProductQty(Integer id, Integer increment) { // 增量庫存
		if(id == null || increment == null || increment < 0) {
			return null;
		}
		return productDao.addProductQty(id, increment);
	}
	
	public Product reduceProductQty(Integer id, Integer decrement) { // 減量庫存
		if(id == null || decrement == null || decrement < 0) {
			return null;
		}
		// 減量後庫存不可 < 0
		Product product = getProductById(id);
		if(product.getQty() - decrement < 0) {
			return null;
		}
		return productDao.reduceProductQty(id, decrement);
	}
	
	public Boolean deleteProductById(Integer id) {
		return id == null ? false : productDao.deleteProductById(id);
	}
}
