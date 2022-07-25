package com.revature.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.revature.data.ProductRepository;

 public class ProductServiceTest {
	
	@Mock
    private ProductRepository ps;
	 
	@BeforeAll
	public void setUp() {
	}
	
	@AfterAll
	public void tearDown() {
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
