package com.example.demo.mq;

public class Main {

	public static void main(String[] args) {
		MessageQueue queue = new MessageQueue();
		Producer producer = new Producer(queue, 10);
		Consumer consumer = new Consumer(queue);
		
		// 啟動消費者 
		new Thread(consumer).start();
		
		// 啟動生產者
		while (producer.getCoffeeStock() > 0) {
			
		}
	}

}
