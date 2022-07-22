package com.revature.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.ProductRepository;
import com.revature.exception.ProductNotFoundException;
import com.revature.model.Product;

@Service
public class ProductService {

//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	ProductRepository productRepository;
//
//	public Set<Product> findAll() {
//		return productRepository.findAll().stream().collect(Collectors.toSet());
//	}
//
//	public Product getByProductName(String product) {
//		return productRepository.findByName(product)
//				.orElseThrow(() -> new ProductNotFoundException("No Product found with product name: " + product));
//	}
//
//	public Product getBySku(Long sku) {
//		if (sku <= 0) {
//			log.warn("Sku cannot be Zero: {}", sku);
//			return null;
//		}
//		return (Product) productRepository.findBySku(sku);
//	}
//
//	public Product add(Product product) {
//		Product returnedProduct = productRepository.save(product);
//		if (returnedProduct.getSku() > 0) {
//			log.info("Successfully returned product with sku {}", returnedProduct.getSku());
//		} else {
//			log.warn("Could not add product");
//		}
//		return returnedProduct;
//	}
//
//	public Product update(Product product) {
//		return productRepository.save(product);
//	}
//
//	public boolean delete(Product sku) {
//		productRepository.deleteBySku(sku);
//
//		return !(productRepository.exists((Product) sku));
//	}
	
}
