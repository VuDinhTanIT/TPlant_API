package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
	