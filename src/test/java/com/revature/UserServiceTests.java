package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.Random;

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
	void testAddUserReturnsNewPKId() {
		dummyUser = new User(0, "toddjones", "password1", "tjones@me.com", new LinkedList<Product>());
		
		Random r = new Random();
		int fakePK = r.nextInt(100);
		
		when(mockUr.insert(dummyUser)).thenReturn(fakePK);
		User registerUser = us.add(dummyUser);
		
		assertEquals(registerUser.getId(), fakePK);
	}
	
	@Test
	void testLoginReturnUser() {
		dummyUser = new User(0, "toddjones", "password1", "tjones@me.com", new LinkedList<Product>());
		
		String username = "toddjones";
		String password = "password1";
		
		when(mockUr.getByUsername(username)).thenReturn(dummyUser);
		
		User loggeUser = us.login(username, password);
		
		assertEquals(loggeUser.getUsername(), username);
	}

}
