package com.vku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vku.models.OrderDetail;
import com.vku.repositories.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
	private final OrderDetailRepository orderDetailRepository;

	@Autowired
	public OrderDetailService(OrderDetailRepository orderDetailRepository) {
		this.orderDetailRepository = orderDetailRepository;
	}

	public List<OrderDetail> getAllCategories() {
		return orderDetailRepository.findAll();
	}

	public Optional<OrderDetail> getOrderDetailById(Long orderDetailId) {
		return orderDetailRepository.findById(orderDetailId);
	}

	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}

	public void deleteOrderDetail(Long orderDetailId) {
		orderDetailRepository.deleteById(orderDetailId);
	}
}