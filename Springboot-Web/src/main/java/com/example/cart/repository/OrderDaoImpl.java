package com.example.cart.repository;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Item;
import com.example.cart.model.po.Order;

@Repository("InMemoryOrder")
public class OrderDaoImpl implements OrderDao {
	private static List<Order> orders = new CopyOnWriteArrayList<>();
	private static List<Item> items = new CopyOnWriteArrayList<>();
	
	static {
		orders.add(new Order(1, "2024-01-01", 1)); // id, date, customerId
		orders.add(new Order(2, "2024-01-02", 2)); // id, date, customerId
		orders.add(new Order(3, "2024-01-03", 1)); // id, date, customerId
		orders.add(new Order(4, "2024-01-04", 3)); // id, date, customerId
		
		items.add(new Item(1, 1, 1, 5)); // id, orderId, productId, amount
		items.add(new Item(2, 1, 2, 2)); // id, orderId, productId, amount
		items.add(new Item(3, 2, 2, 3)); // id, orderId, productId, amount
		items.add(new Item(4, 2, 3, 4)); // id, orderId, productId, amount
		items.add(new Item(5, 3, 1, 7)); // id, orderId, productId, amount
		items.add(new Item(6, 3, 3, 8)); // id, orderId, productId, amount
		items.add(new Item(7, 4, 2, 9)); // id, orderId, productId, amount
		
	}
	
	@Override
	public List<Order> getAllOrders() {
		return orders;
	}

	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {
		return orders.stream()
				.filter(order -> order.getCustomerId().equals(customerId))
				.toList();
	}

	@Override
	public Order getOrderById(Integer orderId) {
		return orders.stream()
				.filter(order -> order.getId().equals(orderId))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Item getItemById(Integer itemId) {
		return items.stream()
				.filter(item -> item.getId().equals(itemId))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Item> getItemsByOrderId(Integer orderId) {
		return items.stream()
				.filter(item -> item.getOrderId().equals(orderId))
				.toList();
	}

	@Override
	public Boolean isProductInOrder(Integer productId) {
		return items.stream()
				.anyMatch(item -> item.getProductId().equals(productId));
	}

	@Override
	public Order getOrderByCustomerIdAndDate(Integer customerId, String date) {
		return orders.stream()
				.filter(order -> order.getCustomerId().equals(customerId) && order.getDate().equals(date))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Order addOrder(Order order) {
		int maxId = orders.stream().mapToInt(o -> o.getId()).max().orElse(0);
		order.setId(maxId + 1);
		orders.add(order);
		return order;
	}

	@Override
	public Item addOrReduceOrderItem(Integer orderId, Integer productId, Integer amount) {
		// 先找到 Item 中是否已存在 orderId 與 productId
		Optional<Item> optItem = items.stream()
				.filter(item -> item.getOrderId().equals(orderId) && item.getProductId().equals(productId))
				.findFirst();
		
		// 若 Item 中不存在 orderId 與 productId 則新增一筆
		if(optItem.isEmpty()) {
			int maxId = items.stream().mapToInt(item -> item.getId()).max().orElse(0);
			Item item = new Item(maxId + 1, orderId, productId, amount);
			items.add(item);
			return item;
		}
		
		// 若 Item 中已存在 orderId 與 productId 則 amount 進行累加
		Item updateItem = optItem.get();
		updateItem.setAmount(updateItem.getAmount() + amount); // amount 的正負會影響增減
		if(updateItem.getAmount() <= 0) {
			items.remove(updateItem); // 若數量為 0 則移除該筆訂單項目
			return null;
		}		
		return updateItem;
	}

	@Override
	public Order updateOrder(Order order) {
		// 新找到 orders 中是否有該筆資料
		Optional<Order> orderOpt = orders.stream().filter(o -> o.getId().equals(order.getId())).findFirst();
		if(orderOpt.isEmpty()) {
			return null; // 資料庫無該筆訂單紀錄
		}
		Order updateOrder = orderOpt.get(); // 得到要修改的訂單改得
		updateOrder.setDate(order.getDate());
		updateOrder.setCustomerId(order.getCustomerId());
		return updateOrder;
	}

	@Override
	public Boolean deleteOrder(Integer orderId) {
		return orders.removeIf(order -> order.getId().equals(orderId));
	}

	@Override
	public Boolean deleteOrderItem(Integer itemId) {
		return items.removeIf(item -> item.getId().equals(itemId));
	}

}
