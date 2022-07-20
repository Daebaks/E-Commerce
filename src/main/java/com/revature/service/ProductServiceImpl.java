package com.revature.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ProductRepository;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
}
