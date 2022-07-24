package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getReferenceBySku(Long id);

}
