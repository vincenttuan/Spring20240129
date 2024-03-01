package com.spring.core.session07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.core.session07.dao.BookDao;

@Service
public class BookOneServiceImpl implements BookOneService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public void buyOne(String username, Integer bookId) {
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 修改(減去)書本庫存
		bookDao.reduceBookStock(bookId, 1); // 固定放  1
		// 3. 修改(減去)客戶餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}
	
}
