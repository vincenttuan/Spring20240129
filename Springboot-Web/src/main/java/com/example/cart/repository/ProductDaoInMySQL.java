package com.example.cart.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Product;

@Repository("InMySQLproduct")
public class ProductDaoInMySQL implements ProductDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
	
	@Override
	public List<Product> queryAllProducts() {
		String sql = "select id, name, cost, price, qty from product order by id";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Product getProductById(Integer id) {
		String sql = "select id, name, cost, price, qty from product where id = ?";
		try {
			Product product = jdbcTemplate.queryForObject(sql, rowMapper, id);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
