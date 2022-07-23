package com.revature;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.data.ProductRepository;
import com.revature.model.Product;
import com.revature.service.ProductService;



class ProductServiceTests {

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
		dummyProduct = null;
		mockPr = null;
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
