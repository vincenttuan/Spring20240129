package com.example.cart.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Product;

@Repository("InMemory")
public class ProductDaoInMemory implements ProductDao {
	private static List<Product> products = new CopyOnWriteArrayList<>();
	
	static {
		products.add(new Product(1, "西瓜", 100, 200, 10));
		products.add(new Product(2, "烤腸", 12, 45, 15));
		products.add(new Product(3, "肉圓", 15, 60, 25));
	}
	
	@Override
	public List<Product> queryAllProducts() {
		return products;
	}

	@Override
	public Product getProductById(Integer id) {
		return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseGet(null);
	}

	@Override
	public Product addProduct(Product product) {
		// 得到 maxId
		int maxId = products.stream().mapToInt(Product::getId).max().orElse(0);
		Integer id = maxId + 1;
		product.setId(id);
		// 儲存
		products.add(product);
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		// 先取得要修改的 product 物件
		Product updateProduct = getProductById(product.getId());
		if(updateProduct == null) {
			return null;
		}
		updateProduct.setName(product.getName());
		updateProduct.setCost(product.getCost());
		updateProduct.setPrice(product.getPrice());
		updateProduct.setQty(product.getQty());
		return updateProduct;
	}

	@Override
	public Product updateProductQty(Integer id, Integer qty) {
		// 先取得要修改的 product 物件
		Product updateProduct = getProductById(id);
		if(updateProduct == null) {
			return null;
		}
		updateProduct.setQty(qty);
		return updateProduct;
	}

	@Override
	public Product addProductQty(Integer id, Integer increment) {
		// 先取得要修改的 product 物件
		Product updateProduct = getProductById(id);
		if(updateProduct == null) {
			return null;
		}
		Integer lastQty = updateProduct.getQty() + increment;
		updateProduct.setQty(lastQty);
		return updateProduct;
	}

	@Override
	public Product reduceProductQty(Integer id, Integer decrement) {
		return addProductQty(id, decrement * -1);
	}

	@Override
	public Boolean deleteProductById(Integer id) {
		return products.removeIf(p -> p.getId().equals(id));
	}
	
	
}
