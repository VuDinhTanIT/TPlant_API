package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
	