package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Product;
import com.revature.model.User;
import com.revature.service.ProductService;

@RestController
@RequestMapping("products")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductService productService;

	
	@GetMapping("instock")
	public List<Product> getInStockProducts(){
		return productService.getInStock();
	}
	
	@GetMapping("outofstock")
	public List<Product> getOutOfStockProducts(){
		return productService.getOutOfStock();
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.findAll();
	}
	
	//Get by sku
	@GetMapping("{sku}")  // -> localhost:8080/product/2 -> pull info for product with sku 2
	public Product findBySku(@PathVariable("sku")Long sku) {
		return productService.findBySku(sku);
	}
	
	@PutMapping
	public Product updateProduct(@RequestBody Product product) {
		return productService.update(product);
	}
	
}
