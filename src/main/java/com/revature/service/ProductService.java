package com.revature.service;

import java.util.ArrayList;
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
	
	// Constructor
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> findAll() {
		log.info("findAll products invoked");
		return productRepository.findAll();
	}

	public Product findBySku(Long sku) {
		log.info("findBySku products invoked");
		return productRepository.getReferenceById(sku);
	}

	public Product update(Product product) {
		log.info("product was updated - changed quantity");
		return productRepository.save(product);
	}
	public List<Product> getInStock() {
		log.info("getInStock products invoked");
		List<Product> available = new ArrayList<>();
		for (Product p : productRepository.findAll()) {
			if (p.getQuantity() > 0) {
				available.add(p);
			}
		}
		return available;
	}
	public List<Product> getOutOfStock() {
		log.info("getOutOfStock products invoked");
		List<Product> notAvailable = new ArrayList<>();
		for (Product p : productRepository.findAll()) {
			if (p.getQuantity() == 0) {
				notAvailable.add(p);
			}
		}
		return notAvailable;
	}

}
