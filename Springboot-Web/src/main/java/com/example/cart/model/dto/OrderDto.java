package com.example.cart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Integer id; // 訂單 id
	private String date; // 訂單日期
	private Integer customerId; // 客戶編號
}
