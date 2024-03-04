package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
	