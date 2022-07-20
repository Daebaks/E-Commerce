package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity(name = "Product")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sku;
	@NotNull(message = "Product name is required.")
	private String name;
	private Double unitprice;
	
	Product(String name, Double unitprice){
		this.name = name;
		this.unitprice = unitprice;
	}

}
