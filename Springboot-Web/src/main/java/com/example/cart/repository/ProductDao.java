package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.po.Product;

public interface ProductDao {
	
	List<Product> queryAllProducts();
	Product getProductById(Integer id);
	Product addProduct(Product product);
	Product updateProduct(Product product);
	Product updateProductQty(Integer id, Integer qty); // 修改庫存(庫存覆蓋)
	Product addProductQty(Integer id, Integer increment); // 增量庫存
	Product reduceProductQty(Integer id, Integer decrement); // 減量庫存
	Boolean deleteProductById(Integer id);
	// 判斷 order item 中是否有指定的 productId 的商品 
	Boolean isProductInOrder(Integer productId);
}
