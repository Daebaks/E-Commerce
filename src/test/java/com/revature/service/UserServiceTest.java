package com.revature.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.revature.data.UserRepository;

@DataJpaTest
class UserServiceTest {
	
	@Autowired
	UserRepository underTest;

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
