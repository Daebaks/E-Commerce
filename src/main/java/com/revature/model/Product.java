package com.revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@Column(name = "sku")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sku;
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "unitprice")
	private Double unitprice;
	
	@Column(name = "category")
	private String category;
	
	
	Product(String name, Double unitprice, String category){
		super();
		this.name = name;
		this.unitprice = unitprice;
		this.category = category;
	}

}
