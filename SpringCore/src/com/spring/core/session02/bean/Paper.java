package com.spring.core.session02.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Paper {
	private Color black;
	private Size b5;
	
	public Paper(Color black, Size a3) {
		this.black = black;
		this.b5 = a3;
	}
}
