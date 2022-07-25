package com.revature.service;

import static org.junit.jupiter.api.Assertions.fail;

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
