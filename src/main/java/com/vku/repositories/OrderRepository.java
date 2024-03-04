package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
	