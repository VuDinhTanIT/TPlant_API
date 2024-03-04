package com.vku.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vku.models.Discount;
import com.vku.services.DiscountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/discounts")
public class DiscountController {
	@Autowired
	private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAllCategories() {
        List<Discount> discounts = discountService.getAllCategories();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/{discountId}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable int discountId) {
        Optional<Discount> discount = discountService.getDiscountById(discountId);
        return discount.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
    	discount.setDiscountId(0);
        Discount savedDiscount = discountService.saveDiscount(discount);
        return new ResponseEntity<>(savedDiscount, HttpStatus.CREATED);
    }

    @PutMapping("/{discountId}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable int discountId, @RequestBody Discount discount) {
        Optional<Discount> existingDiscount = discountService.getDiscountById(discountId);
        if (existingDiscount.isPresent()) {
            discount.setDiscountId(discountId);
            Discount updatedDiscount = discountService.saveDiscount(discount);
            return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{discountId}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable int discountId) {
        Optional<Discount> existingDiscount = discountService.getDiscountById(discountId);
        if (existingDiscount.isPresent()) {
            discountService.deleteDiscount(discountId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}