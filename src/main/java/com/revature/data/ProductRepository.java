package com.revature.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
//	public Product getBySku(Long sku);
	
//	@Query("FROM products WHERE quantity > 0")
//	public List<Product> getAvailable();
//	
//	public Product updateProduct(Product product);
	
//	public Product addProduct();

}
