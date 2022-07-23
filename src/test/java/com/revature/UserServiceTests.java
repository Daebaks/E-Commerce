package com.revature;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.data.UserRepository;
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
		dummyUser.setId(0);
	}

	@After
	void tearDown() {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
