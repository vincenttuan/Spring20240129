package com.example.demo.mq;

// 消費者
public class Consumer implements Runnable {
	
	private final MessageQueue queue;
	
	public Consumer(MessageQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		System.out.println("Consumer 消費者執行...");
		while (true) {
			try {
				// 取得訊息
				String message = queue.consume();
				// 處理訊息
				System.out.println("消費者喝了一杯:" + message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
