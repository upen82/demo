package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;

public class ShoppingServiceTest {

	ShoppingService shoppingService = null;

	@Before
	public void init() {
		shoppingService = new ShoppingService();
	}

	@After
	public void clean() {
		shoppingService = null;
	}

	@Test
	public void emptyCartShouldResultZeroItemsInCart() {
		assertEquals(0, shoppingService.countItems());
	}

	@Test(expected = InvalidProductException.class)
	public void removingNonExistingItemFromCartShouldResultInException() throws InvalidProductException {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		Product watch = new Product(101, "Titan Watch", 45000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);

		shoppingService.removeItem(400);
	}

	@Test
	public void removingAllLoptopItemsFromCartShouldRemoveFromCart() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		Product watch = new Product(101, "Titan Watch", 45000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);

		try {
			shoppingService.removeItem(100);
			shoppingService.removeItem(100);
			shoppingService.removeItem(100);

			Collection<Product> products = shoppingService.cartDetails();
			for (Product product : products) {
				assertNotEquals(100, product.getId());
			}

		} catch (InvalidProductException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void removingItemFromCartShouldReduceCartSize() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		int oldSize = shoppingService.countItems();

		try {
			shoppingService.removeItem(100);
			assertEquals(1, (oldSize - shoppingService.countItems()));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void addMultipleItemsShouldMakeCartSizeToBeMoreThanOne() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		Product watch = new Product(101, "Titan Watch", 45000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		shoppingService.addItem(mobile);

		assertTrue(shoppingService.countItems() > 1);
		assertEquals(5, shoppingService.countItems());
	}

	@Test(expected = NullPointerException.class)
	public void addNullItemShouldProductNullException() {
		Product laptop = null;
		shoppingService.addItem(laptop);
	}

	@Test
	public void addDuplicateItemShouldResultInSameItemAddedTwice() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		assertEquals(2, shoppingService.countItems());
	}

	@Test
	public void addOneItemShouldMakeCartSizeToBeOne() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		shoppingService.addItem(laptop);
		assertEquals(1, shoppingService.countItems());
	}

	@Test
	public void addLaptopInCartShouldResultInLaptopOnly() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		shoppingService.addItem(laptop);
		Collection<Product> products = shoppingService.cartDetails();
		for (Product product : products) {
			assertEquals(100, product.getId());
			assertTrue(product.getName().equals("HP Laptop"));
		}
	}

	@Test
	public void emptyCartShouldAlsoResultInNonNullCart() {
		assertNotNull(shoppingService.cartDetails());
	}
	
	
	@Test
	public void totalPriceShouldResultWithAccurateCartValue() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		Product watch = new Product(101, "Titan Watch", 45000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		shoppingService.addItem(mobile);
		assertEquals(225000.00, shoppingService.totalPrice(),2);
	}


	@Test
	public void emptyingCartShouldReduceCartSizeToBeZero() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		Product watch = new Product(101, "Titan Watch", 45000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		shoppingService.addItem(mobile);
		
		shoppingService.emptyCart();
		
		assertEquals(0, shoppingService.countItems());
	}


	@Test
	public void placeOrderShouldGiveOrderNumber() {
		Product laptop = new Product(100, "HP Laptop", 15000.00, 1);
		Product watch = new Product(101, "Titan Watch", 45000.00, 1);

		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		
		assertNotNull(shoppingService.placeOrder());
		assertTrue(shoppingService.placeOrder().length() > 10);
		assertTrue(shoppingService.placeOrder().startsWith("O"));
		
	}

	

}