package com.revature.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Product;
import com.revature.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

//	public ProductController(ProductService productService) {
//		this.productService = productService;
//	}

	@GetMapping
	public Set<Product> getAll() {
		return productService.findAll();
	}

	@GetMapping("find/{product}")
	public Product findByProductName(@PathVariable("product") String product) {
		return productService.getByProductName(product);
	}

	@GetMapping("findQuery")
	public Product findByProductRequest(@RequestParam("product") String product) {
		return productService.getByProductName(product);
	}

	@GetMapping("findHeader")
	public Product findByHeader(@RequestHeader("product") String product) {
		return productService.getByProductName(product);
	}

	@GetMapping("findHeader2")
	public Product findByHeader2(@RequestHeader HttpHeaders httpHeaders) {
		return productService.getByProductName(httpHeaders.getFirst("product"));
	}

	@PostMapping
	public Product addNewProduct(@RequestBody Product product) {
		return productService.add(product);
	}

	@PutMapping("update")
	public Product updateProduct(@RequestBody Product product) {
		return productService.update(product);
	}

	@DeleteMapping("delete/{sku}")
	public boolean deleteProduct(@PathVariable("sku") Product sku) {
		return productService.delete(sku);
	}

}
