package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name="orders")
@Data // generate getters/setter, toString, hashCode, and equals methods automatically
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	//This will be filled by aggregating the junction table of order_customer and orders table
//	@javax.persistence.OneToMany(cascade = CascadeType.ALL)
//	@javax.persistence.MapKey(name = "items_quantity")
//	private Map<Integer, Integer> items_quantity;   //sku - quantity
	
	
	@JoinColumn(name="sku_order", referencedColumnName="sku")
	@OneToOne
	private Product product;
	
	@Column(name = "quantity_sold")
	private int quantity_sold;
	
	@Column(name = "order_date")
	private LocalDate order_date;

	public Order(Product product, int quantity_sold, LocalDate order_date) {
		super();
		this.product = product;
		this.quantity_sold = quantity_sold;
//		this.order_date = order_date;
	}
	
	
	

	 
	
	
}
