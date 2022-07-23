package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Product;
import com.revature.service.ProductService;

@RestController
@RequestMapping("products")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> getAllProducts(){
		return productService.findAll();
	}
	
//	@GetMapping
//	public Product findBySku(Long sku) {
//		return productService.findBySku(sku);
//	}
	
	
//	@GetMapping("available")
//	public List<Product> getAllAvailableProducts(){
//		return productService.getAvailable();
//	}
	
	@PutMapping
	public Product updateProduct(@RequestBody Product product) {
		return productService.update(product);
	}
	
}
