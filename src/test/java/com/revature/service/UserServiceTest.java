package com.revature.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.data.UserRepository;
import com.revature.model.Product;
import com.revature.model.User;

class UserServiceTest {
	
	private UserService us;
	private UserRepository mockUr;
	private User dummyUser;

	@Before
	void setUp() {
		us = new UserService();
		mockUr = mock(UserRepository.class);
		dummyUser = new User();
		dummyUser.setCart(new LinkedList<Product>());
		dummyUser.setId(0);
	}

	@After
	void tearDown() {
		us = null;
		mockUr = null;
		dummyUser = null;
	}
	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	void testAddToCart() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveFromCart() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCartItems() {
		fail("Not yet implemented");
	}

	@Test
	void testClearCart() {
		fail("Not yet implemented");
	}

}
