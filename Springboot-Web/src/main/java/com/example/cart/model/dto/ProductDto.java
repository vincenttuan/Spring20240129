package com.example.cart.model.dto;

import com.example.cart.model.po.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Integer id;    // 商品 id
	private String name;   // 商品名稱
	private Integer price; // 商品售價
	private Integer qty;   // 商品數量
}
