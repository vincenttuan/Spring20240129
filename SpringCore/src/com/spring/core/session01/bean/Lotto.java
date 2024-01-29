package com.spring.core.session01.bean;

import java.util.Random;

public class Lotto {
	
	private int number;
	
	public Lotto() {
		number = new Random().nextInt();
	}

	@Override
	public String toString() {
		return "Lotto [number=" + number + "]";
	}
	
}
