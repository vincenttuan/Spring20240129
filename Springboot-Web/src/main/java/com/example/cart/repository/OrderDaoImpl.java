package com.example.cart.repository;

import java.util.List;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemById(Integer itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItemsByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isProductInOrder(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderByCustomerIdAndDate(Integer customerId, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item addOrReduceOrderItem(Integer orderId, Integer productId, Integer amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteOrderItem(Integer itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
