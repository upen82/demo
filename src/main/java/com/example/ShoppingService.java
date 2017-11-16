package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingService {

	private Map<Integer, Product> cart = new HashMap<>();

	public void addItem(Product product) {
		int pid = product.getId();
		if (cart.containsKey(pid)) {
			Product existingProduct = cart.get(pid);
			existingProduct.setQty(existingProduct.getQty() + 1);
		} else {
			cart.put(pid, product);
		}
	}

	public int countItems() {
		int count = 0;
		Collection<Product> products = cart.values();
		for (Product product : products) {
			count = count + product.getQty();
		}
		return count;
	}

	public Collection<Product> cartDetails() {
		return cart.values();
	}

	public void removeItem(int pid) throws InvalidProductException {
		if (!cart.containsKey(pid)) {
			throw new InvalidProductException("Product with id : " + pid +
					"not purchased in cart till now!!!");
		} else {
			Product existingProduct = cart.get(pid);
			if (existingProduct.getQty() == 1) {
				cart.remove(pid);
			} else {
				existingProduct.setQty(existingProduct.getQty() - 1);
			}
		}

	}

	public double totalPrice() {
		double total = 0.0;
		Collection<Product> products = cart.values();
		for (Product product : products) {
			double price = product.getQty() * product.getPrice();
			total = total + price;
		}
		return total;
	}

	public void emptyCart() {
		cart.clear();
	}

	public String placeOrder() {
		// DB calls
		return "O" + System.nanoTime();
	}

}
