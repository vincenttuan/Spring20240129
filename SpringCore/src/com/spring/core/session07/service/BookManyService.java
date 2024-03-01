package com.spring.core.session07.service;

public interface BookManyService {
	void buyMany(String username, Integer... bookIds); // 可以買很多書
}
