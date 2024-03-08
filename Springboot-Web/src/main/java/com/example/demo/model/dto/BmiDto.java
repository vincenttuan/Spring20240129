package com.example.demo.model.dto;

// jdk 21: 記錄類
public record BmiDto(Double height, Double weight, Double bmiValue, String bmiResult) {}
/*
public class BmiDto {
	private final Double height;
	private final Double weight;
	private final Double bmiValue;
	private final String bmiResult;
	
	public BmiDto(Double height, Double weight, Double bmiValue, String bmiResult) {
		this.height = height;
		this.weight = weight;
		this.bmiValue = bmiValue;
		this.bmiResult = bmiResult;
	}

	public Double getHeight() {
		return height;
	}

	public Double getWeight() {
		return weight;
	}

	public Double getBmiValue() {
		return bmiValue;
	}

	public String getBmiResult() {
		return bmiResult;
	}
	
}
*/