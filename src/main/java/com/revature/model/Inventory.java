package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Data // generate getters/setter, toString, hashCode, and equals methods automatically
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventory_id;
	
	@Column(name = "sku_inventory")
	private int sku_inventory;
	
	@Column(name = "quantity")
	private int quantity;

	public Inventory(int sku_inventory, int quantity) {
		super();
		this.sku_inventory = sku_inventory;
		this.quantity = quantity;
	}
 
}
