package com.vku.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vku.models.OrderDetail;
import com.vku.services.OrderDetailService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/orderDetails")
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllCategories() {
        List<OrderDetail> orderDetails = orderDetailService.getAllCategories();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long orderDetailId) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        return orderDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
    	orderDetail.setOrderDetailId(null);
        OrderDetail savedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
        return new ResponseEntity<>(savedOrderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long orderDetailId, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        if (existingOrderDetail.isPresent()) {
            orderDetail.setOrderDetailId(orderDetailId);
            OrderDetail updatedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
            return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long orderDetailId) {
        Optional<OrderDetail> existingOrderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        if (existingOrderDetail.isPresent()) {
            orderDetailService.deleteOrderDetail(orderDetailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}