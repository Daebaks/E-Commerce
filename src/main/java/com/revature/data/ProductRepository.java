package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
//	public Optional<Product> findByName(String name);
//	
//	public List<Product> findByCategory(String category);
//	
//	public List<Product> findBySku(Long sku);
//	
//	public List<Product> findByUnitprice(Double unitprice);
//
//	public boolean exists(Product sku);
//
//	public void deleteBySku(Product sku);
}
