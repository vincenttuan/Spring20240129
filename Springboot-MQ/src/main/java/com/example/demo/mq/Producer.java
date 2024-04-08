package com.example.demo.mq;

// 生產者
public class Producer implements Runnable {
	// 訊息對列
	private final MessageQueue queue;
	
	// 咖啡庫存
	private int coffeeStock;
	
	public Producer(MessageQueue queue, int coffeeStock) {
		this.queue = queue;
		this.coffeeStock = coffeeStock;
	}
	
	@Override
	public void run() {
		System.out.println("Producer 生產者執行...");
		System.out.println("目前咖啡庫存: " + coffeeStock);
		// 模擬生產一杯咖啡
		queue.produce("Producer 生產一杯咖啡");
		// 更新庫存
		coffeeStock--;
	}
	
	public int getCoffeeStock() {
		return coffeeStock;
	}
	
}
