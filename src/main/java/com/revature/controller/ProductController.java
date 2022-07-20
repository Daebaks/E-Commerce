package com.revature.controller;

import com.revature.service.ProductService;

public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

}
