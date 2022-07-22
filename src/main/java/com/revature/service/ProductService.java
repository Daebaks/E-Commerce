package com.revature.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.ProductRepository;
import com.revature.model.Product;

@Service
public class ProductService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	
	public Product findBySku(Long sku) {
		return productRepository.getReferenceById(sku);
	}


	public Product update(Product product) {
		return productRepository.save(product);
	}
	

}
