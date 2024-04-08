package com.example.demo.mq;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MessageQueue queue = new MessageQueue();
		Producer producer = new Producer(queue, 10);
		Consumer consumer = new Consumer(queue);
		
		// 啟動消費者 
		new Thread(consumer).start();
		
		// 啟動生產者
		while (producer.getCoffeeStock() > 0) {
			// 啟動執行緒生產咖啡
			new Thread(producer).start();
			Thread.sleep(2000); // delay 2 秒
		}
	}

}
