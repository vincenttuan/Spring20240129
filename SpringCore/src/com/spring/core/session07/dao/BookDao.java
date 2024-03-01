package com.spring.core.session07.dao;

public interface BookDao {
	// 交易服務-查詢
	Integer getBookPrice(Integer bookId); // 取得書本價格
	Integer getBookStock(Integer bookId); // 取得書本庫存
	Integer getWalletBalance(String username); // 取得該名客戶目前餘額
	// 交易服務-更新
	Integer reduceBookStock(Integer bookId, Integer amountToReduce); // 更新書本庫存(減量)
	Integer reduceWalletBalance(String username, Integer bookPrice); // 更新錢包餘額(減量)
}
