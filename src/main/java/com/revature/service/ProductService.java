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
	
//	public List<Product> getAvailable(){
//		return productRepository.getAvailableProducts();
//	}
	
	public Product findBySku(Long sku) {
		return productRepository.getReferenceById(sku);
	}

	public Product add(Product product) {
		return productRepository.save(product);
	}
	
	public Product update(Product product) {
		return productRepository.save(product);
	}
	
	public boolean delete(Product product) {
		productRepository.delete(product);
		return productRepository.existsById(product.getSku());
	}
	
//	public Product add(Product product) {
//		Product returnedProduct = productRepository.save(product);
//		if (returnedProduct.getSku() > 0) {
//			log.info("Successfully returned product with sku {}", returnedProduct.getSku());
//		} else {
//			log.warn("Could not add product");
//		}
//		return returnedProduct;
//	}
}
