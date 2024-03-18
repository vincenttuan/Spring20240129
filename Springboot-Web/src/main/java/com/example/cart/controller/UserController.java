package com.example.cart.controller;

import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.model.response.StatusMessage;
import com.example.demo.model.response.ApiResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/name")
	public ResponseEntity<ApiResponse<String>> getLoginUsername(Principal principal) {
		String username = String.format("{'username': '%s'}", principal.getName()); // 取得登入者的名字
		ApiResponse<String> apiResponse = new ApiResponse<>(true, StatusMessage.成功.name(), username);
		return ResponseEntity.ok(apiResponse);
	}
		
}
