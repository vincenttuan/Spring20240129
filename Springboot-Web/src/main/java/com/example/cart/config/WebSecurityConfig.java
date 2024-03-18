package com.example.cart.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.cart.service.CustomerService;

@EnableWebSecurity // 啟用 Spring Security
@Configuration // 這是一個配置類
public class WebSecurityConfig {
	
	@Autowired
	private CustomerService customerService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		// 使用者集合
		Collection<UserDetails> users = new ArrayList<>();
		// 將每一個 customer 轉成 UserDetails, 並放入到 users 中
		customerService.findAllCustomers().forEach(customer -> {
			UserDetails user = User.builder()
					.username(customer.getUsername())
					.password(encoder.encode(customer.getPassword())) // 加密
					.roles("USER")
					.build();
			users.add(user);
		});
		
		return new InMemoryUserDetailsManager(users);
	}
	
}
