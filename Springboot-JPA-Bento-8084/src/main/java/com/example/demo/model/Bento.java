package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bento")
@Data
public class Bento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="bento_name", unique = true, nullable=false, length=50)
	private String name;
	
	@Column(name="bento_price", nullable=false, columnDefinition="integer default 0")
	private Integer price;
	
	@Column(name="bento_quantity", nullable=false, columnDefinition="integer default 0")
	private Integer quantity; // 庫存
	
}
