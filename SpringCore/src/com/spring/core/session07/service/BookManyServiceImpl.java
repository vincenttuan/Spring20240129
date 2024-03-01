package com.spring.core.session07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookManyServiceImpl implements BookManyService {
	@Autowired
	private BookOneService bookOneService;

	@Override
	public void buyMany(String username, Integer... bookIds) {
		// 要買很多本, 一本一本買
		for(Integer bookId : bookIds) {
			bookOneService.buyOne(username, bookId);
		}
	}
	
}
