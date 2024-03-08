package com.example.demo.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<ApiResponse<Integer>> pass(@RequestParam("score") Integer score) {
		ApiResponse<Integer> apiResponse = new ApiResponse<>();
		apiResponse.setData(score);
		if(score >= 60) {
			apiResponse.setStatus(true);
			apiResponse.setMessage("及格");
		} else {
			apiResponse.setStatus(false);
			apiResponse.setMessage("不及格");
		}
		return ResponseEntity.ok(apiResponse);
	}
	
}
