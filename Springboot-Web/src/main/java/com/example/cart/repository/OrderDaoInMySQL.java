package com.example.cart.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Item;
import com.example.cart.model.po.Order;

@Repository("InMySQLOrder")
public class OrderDaoInMySQL implements OrderDao {

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
