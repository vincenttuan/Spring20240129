package com.spring.core.session07.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer getBookPrice(Integer bookId) { // 取得書本價格
		String sql = "select book_price from book where book_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}

	@Override
	public Integer getBookStock(Integer bookId) { // 取得書本庫存
		String sql = "select book_amount from stock where book_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}

	@Override
	public Integer getWalletBalance(String username) { // 取得該名客戶目前餘額
		String sql = "select balance from wallet where username = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, username);
	}

	@Override
	public Integer reduceBookStock(Integer bookId, Integer amountToReduce) { // 更新書本庫存(減量)
		// 1. 檢查庫存
		Integer bookStock = getBookStock(bookId); // 取得該書庫存
		if(bookStock < amountToReduce) { // 庫存不足
			throw new RuntimeException(String.format("book_id: %d 庫存不足 (%d < %d)%n", bookId, bookStock, amountToReduce));
		}
		// 2. 修改庫存
		String sql = "update stock set book_amount = book_amount - ? where book_id = ?";
		return jdbcTemplate.update(sql, amountToReduce, bookId);
	}

	@Override
	public Integer reduceWalletBalance(String username, Integer bookPrice) { // 更新錢包餘額(減量)
		// 1. 檢查帳戶餘額
		Integer balance = getWalletBalance(username); // 取得客戶目前餘額
		if(balance < bookPrice) { // 餘額不足
			throw new RuntimeException(String.format("username: %s 餘額不足 (%d < %d)%n", username, balance, bookPrice));
		}
		
		// 2. 修改帳戶餘額
		String sql = "update wallet set balance = balance - ? where username = ?";
		return jdbcTemplate.update(sql, bookPrice, username);
	}
	
}









