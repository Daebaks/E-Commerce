package com.revature.service;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.revature.data.ProductRepository;

@AutoConfigureTestDatabase
class ProductServiceTest {
	
	@Autowired
	private ProductRepository underTest;
	
	@Before
	void setUp() {
	}
	
	@After
	void tearDown() {
	}

	@Test
	void testFindAll() {
		
	}

	@Test
	void testFindBySku() {
		
	}

	@Test
	void testUpdate() {
		
	}

	@Test
	void testGetInStock() {
		
	}

	@Test
	void testGetOutOfStock() {
		
	}

}
