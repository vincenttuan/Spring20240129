package com.example.demo.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.BmiDto;
import com.example.demo.model.response.ApiResponse;

@RestController
@RequestMapping("/data")
public class DataController {
	
	@GetMapping("/today")
	public ResponseEntity<ApiResponse<Date>> today() {
		Date today = new Date();
		ApiResponse<Date> apiResponse = new ApiResponse<>(true, "成功", today);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/lotto")
	public ResponseEntity<ApiResponse<Integer>> lotto() {
		Integer lottoNumber = new Random().nextInt(100);
		ApiResponse<Integer> apiResponse = new ApiResponse<>(true, "成功", lottoNumber);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/pass")
	public ResponseEntity<ApiResponse<Integer>> pass(@RequestParam(value = "score", defaultValue = "-1") Integer score) {
		ApiResponse<Integer> apiResponse = new ApiResponse<>();
		
		apiResponse.setData(score);
		if(score >= 60) {
			apiResponse.setStatus(true);
			apiResponse.setMessage("及格");
		} else if (score >= 0) {
			apiResponse.setStatus(true);
			apiResponse.setMessage("不及格");
		} else {
			apiResponse.setStatus(false);
			apiResponse.setMessage("請輸入分數");
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	// http://localhost:8080/data/bmi?h=170&w=60
	// 可以回應身高,體重,bmi與result
	// bmi <= 18 : 過輕, bmi > 23 : 過重, 其餘正常
	// 提示: 嘗試建立一個 BMI 的 DTO 物件來封裝資料訊息
	@GetMapping("/bmi")
	public ResponseEntity<ApiResponse<BmiDto>> calcBmi(@RequestParam("h") Double height, @RequestParam("w") Double weight) {
		Double bmiValue = weight / Math.pow(height/100.0, 2);
		String bmiResult = bmiValue <= 18 ? "過輕" : bmiValue > 23 ? "過重" : "正常";
		BmiDto bmiDto = new BmiDto(height, weight, bmiValue, bmiResult);
		ApiResponse<BmiDto> apiResponse = new ApiResponse<>(true, "成功", bmiDto);
		return ResponseEntity.ok(apiResponse);
	}
	
}
