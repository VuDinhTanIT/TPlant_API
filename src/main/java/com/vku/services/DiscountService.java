package com.vku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vku.models.Discount;
import com.vku.repositories.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllCategories() {
        return discountRepository.findAll();
    }

    public Optional<Discount> getDiscountById(int discountId) {
        return discountRepository.findById(discountId);
    }

    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public void deleteDiscount(int discountId) {
        discountRepository.deleteById(discountId);
    }
}