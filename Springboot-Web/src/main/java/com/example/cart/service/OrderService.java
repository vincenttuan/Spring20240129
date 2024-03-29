package com.example.cart.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.CustomerDto;
import com.example.cart.model.dto.ItemDto;
import com.example.cart.model.dto.OrderDto;
import com.example.cart.model.dto.ProductDto;
import com.example.cart.model.po.Item;
import com.example.cart.model.po.Order;
import com.example.cart.model.po.Product;
import com.example.cart.repository.CustomerDao;
import com.example.cart.repository.OrderDao;
import com.example.cart.repository.ProductDao;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public ItemDto getItemById(Integer itemId) {
		Item item = orderDao.getItemById(itemId);
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setOrderId(item.getOrderId());
		ProductDto productDto = productService.getProductById(item.getProductId());
		itemDto.setProductDto(productDto);
		itemDto.setAmount(item.getAmount());
		return itemDto;
	}

	public List<ItemDto> getItemsByOrderId(Integer orderId) {
		List<Item> items = orderDao.getItemsByOrderId(orderId);
		return items.stream().map(item -> getItemById(item.getId())).toList();
	}
	
	public OrderDto getOrderById(Integer orderId) {
		Order order = orderDao.getOrderById(orderId);
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setDate(order.getDate());
		// 若訂單日期是今天，則訂單可以更新
		String today = sdf.format(new Date());
		orderDto.setUpdatable(order.getDate().equals(today));
		
		CustomerDto customerDto = customerService.getCustomerById(order.getCustomerId());
		orderDto.setCustomerDto(customerDto);
		List<ItemDto> itemDtos = getItemsByOrderId(orderId);
		orderDto.setItemDtos(itemDtos);
		
		// 計算訂單總金額
		Integer total = 0;
		if (itemDtos.size() > 0) {
			total = itemDtos.stream().mapToInt(itemDto -> itemDto.getProductDto().getPrice() * itemDto.getAmount()).sum();
		}
		orderDto.setTotal(total);
		return orderDto;
	}
	
	public List<OrderDto> getAllOrders() {
		List<Order> orders = orderDao.getAllOrders();
		return orders.stream().map(order -> getOrderById(order.getId())).toList();
	}
	
	public List<OrderDto> getOrdersByUsername(String customerName) {
		CustomerDto customerDto = customerService.getCustomerByUsername(customerName);
		return getOrdersByCustomerId(customerDto.getId());
	}
	
	public List<OrderDto> getTodayOrdersByUsername(String customerName) {
		List<OrderDto> orderDtos = getOrdersByUsername(customerName);
		// 取得今天的日期
		String today = sdf.format(new Date());
		// 篩選出今天的訂單
		return orderDtos.stream().filter(orderDto -> orderDto.getDate().equals(today)).toList();
	}
	
	// 不含今日
	public List<OrderDto> getHistoryOrdersByUsername(String customerName) {
		List<OrderDto> orderDtos = getOrdersByUsername(customerName);
		// 取得今天的日期
		String today = sdf.format(new Date());
		// 篩選出今天以前的訂單
		return orderDtos.stream().filter(orderDto -> !orderDto.getDate().equals(today)).toList();
	}
	
	public List<OrderDto> getOrdersByCustomerId(Integer customerId) {
		List<Order> orders = orderDao.getOrdersByCustomerId(customerId);
		List<OrderDto> orders1 = orders.stream()
			    .map(order -> getOrderById(order.getId()))
			    .sorted((order1, order2) -> order2.getId() - order1.getId()) // 按照 ID 降序排序
			    .collect(Collectors.toList());
		return orders1;
	}

	public OrderDto addOrder(String username) {
		// 用戶今天是否已經有訂單
		Integer customerId = customerService.getCustomerByUsername(username).getId();
		String today = sdf.format(new Date());
		Order order = orderDao.getOrderByCustomerIdAndDate(customerId, today);
		if (order != null) {
			return getOrderById(order.getId());
		}
		// 若無訂單，則新增訂單
		order = new Order();
		order.setCustomerId(customerId);
		order.setDate(today);
		order = orderDao.addOrder(order);
		return getOrderById(order.getId());
	}

	public ItemDto addOrReduceOrderItem(Integer orderId, Integer productId, Integer amount) {
		// 檢查該商品的庫存是否足夠 ?
		Product product = productDao.getProductById(productId);
		if (product.getQty() < amount) { // 庫存不足
			return null;
		}
		// 庫存足夠, 則新增或減少庫存
		productDao.updateProductQty(productId, product.getQty() - amount);
		// 新增或減少訂單項目
		Item item = orderDao.addOrReduceOrderItem(orderId, productId, amount);
		if (item == null) {
			return null;
		}
		return getItemById(item.getId());
	}

	public OrderDto updateOrder(Order order) {
		order = orderDao.updateOrder(order);
		return getOrderById(order.getId());
	}
	
	// 刪除訂單
	public Boolean deleteOrder(Integer orderId) {
		return orderDao.deleteOrder(orderId);
	}
	
	// 刪除訂單項目
	public Boolean deleteOrderItem(Integer itemId) {
		// 取得訂單項目的商品ID與購買數量
		Item item = orderDao.getItemById(itemId);
		Integer amount = item.getAmount(); // 購買數量
		Integer productId = item.getProductId(); // 商品 id
		// 刪除訂單項目
		Boolean status = orderDao.deleteOrderItem(itemId);
		if(status) {
			// 將該訂單商品的數量回滾到商品庫存中
			productDao.addProductQty(productId, amount);
		}
		return status;
	}
	
	public OrderDto getOrderByCustomerIdAndDate(Integer customerId, String date) {
		Order order = orderDao.getOrderByCustomerIdAndDate(customerId, date);
        return getOrderById(order.getId());
    }
}
