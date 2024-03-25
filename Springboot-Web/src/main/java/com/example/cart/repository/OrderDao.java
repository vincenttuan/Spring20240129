package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.po.Item;
import com.example.cart.model.po.Order;

public interface OrderDao {
	public List<Order> getAllOrders();
	public List<Order> getOrdersByCustomerId(Integer customerId);
	public Order getOrderById(Integer orderId);
	public Item getItemById(Integer itemId);
	public List<Item> getItemsByOrderId(Integer orderId);
	// order 中是否有指定 productId 的商品
	public Boolean isProductInOrder(Integer productId);
	// 找到該客戶在指定日期的訂單
	public Order getOrderByCustomerIdAndDate(Integer customerId, String date);
	
}
