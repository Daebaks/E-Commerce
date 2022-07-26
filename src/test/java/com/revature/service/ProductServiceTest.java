package com.revature.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.revature.data.ProductRepository;
import com.revature.model.Product;
import com.revature.model.User;

 public class ProductServiceTest {
	
	@Mock
    private ProductRepository pRepo;
	
	private ProductService ps;

	private Product p1;
	private Product p2;
	private Product p3;	
	
	@BeforeAll
	public void setUp() {
		ps = new ProductService(pRepo);
	}
	
	@AfterAll
	public void tearDown() {
		  p1 = null;
		  p2 = null;
		  p3 = null;
		  ps = null;
	}

	/* test findAll() */
	@Test
	void testFindAll() {
		
	}


}
