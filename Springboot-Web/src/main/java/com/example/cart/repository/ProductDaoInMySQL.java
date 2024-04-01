package com.example.cart.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Product;

@Repository("InMySQLproduct")
public class ProductDaoInMySQL implements ProductDao {
	
	@Override
	public List<Product> queryAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProductQty(Integer id, Integer qty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProductQty(Integer id, Integer increment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product reduceProductQty(Integer id, Integer decrement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteProductById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
