package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.ProductRepository;
import com.revature.data.UserRepository;
import com.revature.model.Product;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository pRepo;

	private ProductService ps;

	private Product p1;
	private Product p2;
	private Product p3;

	@BeforeEach
	public void setUp() {
		ps = new ProductService(pRepo);
	}

	@AfterEach
	public void tearDown() {
		p1 = null;
		p2 = null;
		p3 = null;
		ps = null;
	}

	/* test findAll() */
	@Test
	void testFindAllSuccessfully() {
		p1 = new Product("Bike", 20.99, "Sport", 100, "path.jpg");
		p1.setSku(7890L);
		p2 = new Product("Laptop", 300.99, "Electronics", 50, "path2.jpg");
		p2.setSku(78L);
		List<Product> productsList = new ArrayList<>();
		productsList.add(p1);
		productsList.add(p2);
		when(pRepo.findAll()).thenReturn(productsList);
		List<Product> productsListReturned = ps.findAll();

		assertEquals(productsListReturned, productsList);

	}

	/* test findBySku() */
	@Test
	void testFindBySkuSuccessfully() {
		p1 = new Product("Bike", 20.99, "Sport", 100, "path.jpg");
		p1.setSku(7890L);
		when(pRepo.getReferenceById(7890L)).thenReturn(p1);

		p2 = ps.findBySku(p1.getSku());

		assertEquals(p1, p2);

	}

	/* test update() */
	@Test
	void testUpdateSuccessfully() {
		p1 = new Product("Bike", 20.99, "Sport", 100, "path.jpg");
		p1.setSku(7890L);

		// update object
		p2 = new Product("Laptop", 300.99, "Electronics", 50, "path2.jpg");
		p2.setSku(7890L);
		when(pRepo.save(p2)).thenReturn(p2);

		p3 = ps.update(p2);

		assertEquals(p2, p3);

	}
	
	
	
}
