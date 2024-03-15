package com.example.cart.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private Integer id;    // 客戶 id
	private String username;   // 客戶名稱
	private String password;   // 客戶密碼
}
