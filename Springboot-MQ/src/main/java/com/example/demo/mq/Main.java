package com.example.demo.mq;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MessageQueue queue = new MessageQueue();
		Producer producer = new Producer(queue, 10);
		Consumer consumer = new Consumer(queue);
		
		// 啟動消費者 
		new Thread(consumer).start();
		
		// 啟動生產者
		Scanner scanner = new Scanner(System.in);
		while (producer.getCoffeeStock() > 0) {
			// 設定訊息內容
			System.out.print("請設定咖啡名稱(訊息內容):");
			String message = scanner.next();
			producer.setMessage(message);
			
			// 啟動執行緒生產咖啡(發布訊息)
			new Thread(producer).start();
			//Thread.sleep(1000); // delay 1 秒
		}
		scanner.close();
		
		System.out.println("程式結束");
		System.exit(0);
	}

}
