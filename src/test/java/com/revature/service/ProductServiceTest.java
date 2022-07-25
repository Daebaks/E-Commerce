package com.revature.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.data.ProductRepository;
import com.revature.model.Product;

class ProductServiceTest {
	

	private ProductService ps;
	private ProductRepository mockPr;
	private Product dummyProduct;
	
	@Before
	void setUp() {
		ps = new ProductService();
		mockPr =mock(ProductRepository.class);
		dummyProduct = new Product();
		dummyProduct.setSku(0L);
	}
	
	@After
	void tearDown() {
		ps = null;
		mockPr = null;
		dummyProduct = null;
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindBySku() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetInStock() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOutOfStock() {
		fail("Not yet implemented");
	}

}
