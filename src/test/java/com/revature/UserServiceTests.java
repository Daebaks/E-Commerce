package com.revature;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.data.UserRepository;
import com.revature.model.Product;
import com.revature.model.User;
import com.revature.service.UserService;

class UserServiceTests {
	
	private UserService us;
	private UserRepository mockUr;
	private User dummyUser;

	@Before
	void setUp() {
		us = new UserService();
		mockUr = mock(UserRepository.class);
		dummyUser = new User();
		dummyUser.setCart(new LinkedList()<Product>());
		dummyUser.setId(0);
	}

	@After
	void tearDown() {
		us = null;
		mockUr = null;
		dummyUser = null;
	}

	@Test
	void test() {
		dummyUser = new User(0, "toddjones", "password1", "tjones@me.com", new LinkedList<Product>());
		
		fail("Not yet implemented");
	}

}
