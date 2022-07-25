package com.revature.service;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.revature.data.UserRepository;

@AutoConfigureTestDatabase
class UserServiceTest {
	
	@Autowired
	UserRepository underTest;

	@Before
	void setUp() {
	}

	@After
	void tearDown() {
		fail();
	}
	
	@Test
	void testFindAll() {
//		fail("Test Test");
	}

	@Test
	void testGetByUsername() {
	
	}

	@Test
	void testGetById() {
	
	}

	@Test
	void testAdd() {

	}

	@Test
	void testUpdate() {
	
	}

	@Test
	void testDelete() {

	}

	@Test
	void testLogin() {

	}

	@Test
	void testAddToCart() {
		
	}

	@Test
	void testRemoveFromCart() {

	}

	@Test
	void testGetCartItems() {

	}

	@Test
	void testClearCart() {

	}

}
