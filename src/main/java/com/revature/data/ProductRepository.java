package com.revature.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
