package com.example.demo.mq;

import java.util.LinkedList;
import java.util.Queue;

// 訊息對列
public class MessageQueue {
	
	private Queue<String> queue = new LinkedList<>();
	
	// 生產 (生產一個訊息)
	public synchronized void produce(String message) {
		queue.add(message); // 將訊息放到對列中
		notify(); // 通知消費者有新消息
	}
	
	// 消費 (消費一個訊息)
	public synchronized String consume() throws InterruptedException {
		while (queue.isEmpty()) {
			System.out.println("消費者等待中...");
			wait(); // 如果對列是空的, 消費者進入等待 
		}
		
		String message = queue.poll(); // 消費, 移除對列的資訊
		return message; // 將所消費的資訊回傳
	}
	
	
}
