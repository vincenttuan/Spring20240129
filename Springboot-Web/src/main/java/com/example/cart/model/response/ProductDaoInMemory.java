package com.example.cart.model.response;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.cart.model.po.Product;

@Repository
public class ProductDaoInMemory implements ProductDao {
	private static List<Product> products = new CopyOnWriteArrayList<>();

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProductQty(Integer id, Integer increment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product reduceProductQty(Integer id, Integer decrement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteProductById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
