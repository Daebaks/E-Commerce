package com.revature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;

}
