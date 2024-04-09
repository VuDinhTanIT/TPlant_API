package com.vku.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryCategoryId(int categoryId, PageRequest pageable);

    List<Product> findByCategoryCategoryId(int categoryId);

}
	