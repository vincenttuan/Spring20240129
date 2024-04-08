package com.example.demo.mq;

// 生產者
public class Producer implements Runnable {
	// 訊息對列
	private final MessageQueue queue;
	
	// 咖啡庫存
	private int coffeeStock;
	
	// 訊息內容
	private String message;
	
	public Producer(MessageQueue queue, int coffeeStock) {
		this.queue = queue;
		this.coffeeStock = coffeeStock;
	}
	
	@Override
	public void run() {
		System.out.println("Producer 生產者執行...");
		System.out.println("目前咖啡庫存: " + coffeeStock);
		// 模擬生產一杯 XX 咖啡
		queue.produce(message);
		// 更新庫存
		coffeeStock--;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCoffeeStock() {
		return coffeeStock;
	}
	
}
