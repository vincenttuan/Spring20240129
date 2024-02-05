package com.spring.core.session03.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
	private Integer id; // 課程代號
	private String name; // 課程名稱
	private Integer credit; // 課程學分
	
	
}
